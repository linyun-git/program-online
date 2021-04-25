package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.EnvironmentDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.result.WorkspaceInfoResult;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.entity.WorkspaceEnvironmentRelation;
import ynu.it.linyun.server.local.WorkspaceIo;
import ynu.it.linyun.server.mapper.WorkspaceEnvironmentRelationMapper;
import ynu.it.linyun.server.mapper.WorkspaceMapper;
import ynu.it.linyun.server.service.WorkspaceEnvironmentRelationService;
import ynu.it.linyun.server.service.WorkspaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Service
public class WorkspaceServiceImpl extends ServiceImpl<WorkspaceMapper, Workspace> implements WorkspaceService {
    @Autowired
    private WorkspaceMapper workspaceMapper;
    @Autowired
    private WorkspaceIo workspaceIo;
    @Autowired
    private WorkspaceEnvironmentRelationMapper workspaceEnvironmentRelationMapper;
    @Autowired
    private WorkspaceEnvironmentRelationService workspaceEnvironmentRelationService;

    @Override
    public Result queryList(QueryDto queryDto) {
        QueryWrapper<Workspace> queryWrapper = new QueryWrapper<Workspace>();
        queryWrapper.like("name", queryDto.getQuery());
        Page<Workspace> page = new Page<Workspace>(queryDto.getPageIndex(), queryDto.getPageSize());
        workspaceMapper.selectPage(page, queryWrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(page.getRecords());
        queryResult.setCount(page.getTotal());
        return Result.success().data(queryResult);
    }

    @Override
    public Result add(User user, Workspace workspace, List<EnvironmentDto> environments) {
        if (null == user) {
            throw new BusinessException("未登录");
        }
        String name = workspace.getName();
        QueryWrapper<Workspace> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (getOne(queryWrapper) != null) {
            return Result.fail("400").msg("同名仓库已存在");
        }
        String auth = workspace.getAuthorityType();
        String directoryCode = Md5.toMd5(name + auth + System.currentTimeMillis());
        workspace.setDirectoryCode(directoryCode);
        workspace.setCreator(user.getId());
        workspace.setCreateDate("2021-04-25");
        workspaceIo.createWorkspace(workspace);
        workspaceMapper.insert(workspace);
        for (var environment : environments) {
            WorkspaceEnvironmentRelation workspaceEnvironmentRelation = new WorkspaceEnvironmentRelation();
            workspaceEnvironmentRelation.setWorkspaceId(workspace.getId());
            workspaceEnvironmentRelation.setEnvironmentName(environment.getName());
            workspaceEnvironmentRelation.setEnvironmentVersion(environment.getVersion());
            workspaceEnvironmentRelationMapper.insert(workspaceEnvironmentRelation);
        }
        return Result.success().data(workspace);
    }

    public Result info(User user, Integer workspaceId) {
        // 将user设为非空的
        User finalUser = null == user ? new User() : user;
        QueryWrapper<Workspace> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", workspaceId).and(i -> i.eq("authority_type", "public").or().eq("creator", finalUser.getId()));
        Workspace workspace = getOne(queryWrapper);
        if (workspace == null) {
            throw new BusinessException("404");
        }
        QueryWrapper<WorkspaceEnvironmentRelation> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("workspace_id", workspace.getId());
        List<WorkspaceEnvironmentRelation> workspaceEnvironmentRelations = workspaceEnvironmentRelationService.list(queryWrapper1);
        List<Environment> environments = new ArrayList<>();
        for (var workspaceEnvironmentRelation: workspaceEnvironmentRelations) {
            Environment environment = new Environment();
            environment.setName(workspaceEnvironmentRelation.getEnvironmentName());
            environment.setVersion(workspaceEnvironmentRelation.getEnvironmentVersion());
            environments.add(environment);
        }
        WorkspaceInfoResult workspaceInfoResult = new WorkspaceInfoResult(workspace, environments);
        return Result.success().data(workspaceInfoResult);
    }
}
