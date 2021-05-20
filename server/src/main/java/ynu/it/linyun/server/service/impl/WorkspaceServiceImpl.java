package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.result.WorkspaceInfoResult;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.entity.WorkspaceEnvironmentRelation;
import ynu.it.linyun.server.mapper.WorkspaceEnvironmentRelationMapper;
import ynu.it.linyun.server.mapper.WorkspaceMapper;
import ynu.it.linyun.server.service.EnvironmentService;
import ynu.it.linyun.server.service.WorkspaceEnvironmentRelationService;
import ynu.it.linyun.server.service.WorkspaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    private WorkspaceEnvironmentRelationMapper workspaceEnvironmentRelationMapper;
    @Autowired
    private WorkspaceEnvironmentRelationService workspaceEnvironmentRelationService;
    @Autowired
    private EnvironmentService environmentService;

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
    public Result add(User user, Workspace workspace, Environment environment) {
        if (null == user) {
            throw new BusinessException("未登录");
        }
        if (null == environment) {
            throw new BusinessException("环境不存在");
        }
        String auth = workspace.getAuthorityType();
        workspace.setCreator(user.getId());
        workspace.setCreateDate("2021-04-25");
        workspaceMapper.insert(workspace);
        WorkspaceEnvironmentRelation workspaceEnvironmentRelation = new WorkspaceEnvironmentRelation();
        workspaceEnvironmentRelation.setWorkspaceId(workspace.getId());
        workspaceEnvironmentRelation.setEnvironmentId(environment.getId());
        workspaceEnvironmentRelationMapper.insert(workspaceEnvironmentRelation);
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
        WorkspaceEnvironmentRelation workspaceEnvironmentRelation = workspaceEnvironmentRelationService.getOne(queryWrapper1);
        Environment environment = null;
        if (workspaceEnvironmentRelation != null) {
            environment = environmentService.getById(workspaceEnvironmentRelation.getEnvironmentId());
        }
        WorkspaceInfoResult workspaceInfoResult = new WorkspaceInfoResult(workspace, environment);
        return Result.success().data(workspaceInfoResult);
    }
}
