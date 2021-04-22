package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.local.WorkspaceIo;
import ynu.it.linyun.server.mapper.ProjectMapper;
import ynu.it.linyun.server.service.ProjectService;
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
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private WorkspaceIo workspaceIo;

    @Override
    public Result queryList(QueryDto queryDto) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<Project>();
        queryWrapper.like("name", queryDto.getQuery());
        Page<Project> page = new Page<Project>(queryDto.getPageIndex(), queryDto.getPageSize());
        projectMapper.selectPage(page, queryWrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(page.getRecords());
        queryResult.setCount(page.getTotal());
        return Result.success().data(queryResult);
    }

    @Override
    public Result add(Project project) {
        String name = project.getName();
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (getOne(queryWrapper) != null) {
            return Result.fail("400").msg("同名项目已存在");
        }
        String auth = project.getAuthorityType();
        String directoryCode = Md5.toMd5(name + auth + System.currentTimeMillis());
        project.setDirectoryCode(directoryCode);
        int id = projectMapper.insert(project);
        project.setId(id);
        return Result.success().data(project);
    }
}
