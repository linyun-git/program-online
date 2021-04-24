package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.local.WorkspaceIo;
import ynu.it.linyun.server.mapper.WorkspaceMapper;
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
    private WorkspaceIo workspaceIo;

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
    public Result add(Workspace workspace, User user) {
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
        workspaceIo.createWorkspace(workspace);
        int id = workspaceMapper.insert(workspace);
        workspace.setId(id);
        return Result.success().data(workspace);
    }
}
