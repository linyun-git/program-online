package ynu.it.linyun.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ynu.it.linyun.server.common.dto.AddWorkspaceDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.service.WorkspaceService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryDto queryDto) {
        return workspaceService.queryList(queryDto);
    }

    @PostMapping("/add")
    public Result add(@Validated @RequestBody AddWorkspaceDto addWorkSpaceDto) {
        Workspace workspace = new Workspace();
        workspace.setName(addWorkSpaceDto.getName());
        workspace.setAuthorityType(addWorkSpaceDto.getAuthorityType());
        return workspaceService.add(workspace);
    }
}
