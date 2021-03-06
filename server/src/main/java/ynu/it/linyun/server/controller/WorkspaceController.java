package ynu.it.linyun.server.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ynu.it.linyun.server.common.dto.AddWorkspaceDto;
import ynu.it.linyun.server.common.dto.EnvironmentDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.JWTUtil;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.service.EnvironmentService;
import ynu.it.linyun.server.service.UserService;
import ynu.it.linyun.server.service.WorkspaceService;

import java.util.List;

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
    @Autowired
    private UserService userService;
    @Autowired
    private EnvironmentService environmentService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryDto queryDto) {
        return workspaceService.queryList(queryDto);
    }

    @GetMapping("/list/{uid}")
    public Result list(@PathVariable("uid") Integer uid, @RequestHeader("token") String token) {
        User loginUser = userService.getUserByToken(token);
        QueryWrapper<Workspace> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", uid);
        if (loginUser == null || !loginUser.getId().equals(uid)) {
            queryWrapper.eq("authority_type", "public");
        }
        List<Workspace> list = workspaceService.list(queryWrapper);
        return Result.success().data(list);
    }

    @PostMapping("/add")
    public Result add(@Validated @RequestBody AddWorkspaceDto addWorkSpaceDto, @RequestHeader("token") String token) {
        User user = userService.getUserByToken(token);
        Workspace workspace = new Workspace();
        workspace.setName(addWorkSpaceDto.getName());
        workspace.setAuthorityType(addWorkSpaceDto.getAuthorityType());
        workspace.setDescription(addWorkSpaceDto.getDescription());
        Environment environment = environmentService.getById(addWorkSpaceDto.getEnvironmentId());
        return workspaceService.add(user, workspace, environment);
    }

    @GetMapping("/info/{workspaceId}")
    public Result info(@PathVariable("workspaceId") Integer workspaceId, @RequestHeader("token") String token) {
        User user = userService.getUserByToken(token);
        return workspaceService.info(user, workspaceId);
    }
}
