package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.result.PathNode;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.entity.WorkspaceProjectRelation;
import ynu.it.linyun.server.local.WorkspaceIo;
import ynu.it.linyun.server.mapper.ProjectMapper;
import ynu.it.linyun.server.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.it.linyun.server.service.WorkspaceProjectRelationService;
import ynu.it.linyun.server.service.WorkspaceService;

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
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private WorkspaceIo workspaceIo;
    @Autowired
    private WorkspaceService workspaceService;
    @Autowired
    private WorkspaceProjectRelationService workspaceProjectRelationService;

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
    public Result add(User user, Project project, Integer workspaceId) {
        Workspace workspace = workspaceService.getById(workspaceId);
        if (null == workspace) {
            throw new BusinessException("404");
        }
        if (!workspace.getCreator().equals(user.getId())) {
            throw new BusinessException("403");
        }
        String directoryCode = Md5.toMd5(project.getName() + System.currentTimeMillis());
        project.setDirectoryCode(directoryCode);
        project.setCreator(user.getId());
        project.setCreateDate("2021-04-25");
        projectMapper.insert(project);
        workspaceIo.createProject(workspace, project);
        WorkspaceProjectRelation workspaceProjectRelation = new WorkspaceProjectRelation();
        workspaceProjectRelation.setWorkspaceId(workspaceId);
        workspaceProjectRelation.setProjectId(project.getId());
        workspaceProjectRelationService.saveOrUpdate(workspaceProjectRelation);
        return Result.success().data(project);
    }

    @Override
    public Result pathInfo(User user, Integer projectId) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        User finalUser = null == user ? new User() : user;
        queryWrapper
                .eq("id", projectId)
                .and(i -> i.eq("authority_type", "public").or().eq("creator", finalUser.getId()));
        Project project = getOne(queryWrapper);
        if (null == project) {
            throw new BusinessException("404");
        }
        Workspace workspace = workspaceProjectRelationService.getWorkspaceByProject(project);
        List<PathNode> pathNodes = workspaceIo.getProjectPath(workspace, project);
        return Result.success().data(pathNodes);
    }
}
