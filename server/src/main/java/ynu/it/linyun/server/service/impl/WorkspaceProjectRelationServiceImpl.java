package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.entity.WorkspaceProjectRelation;
import ynu.it.linyun.server.mapper.WorkspaceProjectRelationMapper;
import ynu.it.linyun.server.service.WorkspaceProjectRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.it.linyun.server.service.WorkspaceService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Service
public class WorkspaceProjectRelationServiceImpl extends ServiceImpl<WorkspaceProjectRelationMapper, WorkspaceProjectRelation> implements WorkspaceProjectRelationService {

    @Autowired
    private WorkspaceService workspaceService;

    @Override
    public Workspace getWorkspaceByProject(Project project) {
        QueryWrapper<WorkspaceProjectRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", project.getId());
        WorkspaceProjectRelation workspaceProjectRelation = getOne(queryWrapper);
        return workspaceService.getById(workspaceProjectRelation.getWorkspaceId());
    }
}
