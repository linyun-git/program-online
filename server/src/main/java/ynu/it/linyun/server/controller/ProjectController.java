package ynu.it.linyun.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ynu.it.linyun.server.common.dto.AddProjectDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.service.ProjectService;
import ynu.it.linyun.server.service.UserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryDto queryDto) {
        return projectService.queryList(queryDto);
    }

    @PostMapping("/add")
    public Result add(@Validated @RequestBody AddProjectDto addProjectDto, @RequestHeader("token") String token) {
        Project project = new Project();
        project.setName(addProjectDto.getName());
        project.setDescription(addProjectDto.getDescription());
        project.setAuthorityType(addProjectDto.getAuthorityType());
        Integer workspaceId = addProjectDto.getWorkspaceId();
        User user = userService.getUserByToken(token);
        return projectService.add(user, project, workspaceId);
    }

    @GetMapping("/pathInfo/{id}")
    public Result pathInfo(@PathVariable("id") Integer id, @RequestHeader("token") String token) {
        User user = userService.getUserByToken(token);
        return projectService.pathInfo(user, id);
    }
}
