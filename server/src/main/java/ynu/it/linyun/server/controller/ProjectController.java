package ynu.it.linyun.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ynu.it.linyun.server.common.dto.AddProjectDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.service.ProjectService;

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

    @PostMapping("/list")
    public Result list(@RequestBody QueryDto queryDto) {
        return projectService.queryList(queryDto);
    }

    @PostMapping("/add")
    public Result add(@Validated @RequestBody AddProjectDto addProjectDto) {
        Project project = new Project();
        project.setName(addProjectDto.getName());
        project.setAuthorityType(addProjectDto.getAuthorityType());
        return projectService.add(project);
    }
}
