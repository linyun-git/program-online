package ynu.it.linyun.server.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ynu.it.linyun.server.common.dto.LoginDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.dto.RegisterDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.JWTUtil;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.service.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Object index() {
        User user = userService.getById(1L);
        if (null == user) {
            return "未找到该人员信息";
        }
        return user;
    }

    @GetMapping("/remove")
    public Object remove() {
        boolean result = userService.removeById(1L);
        return result ? "成功" : "失败";
    }

    @GetMapping("/login")
    public Result login(@RequestHeader("token") String token) {
        Integer id = null;
        try {
            DecodedJWT jwt = JWTUtil.getTokenInfo(token);
            id = Integer.parseInt(jwt.getClaim("id").asString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = userService.getById(id);
        if (null == user) {
            return Result.fail("403").msg("无效的token");
        }
        return Result.success().data(user);
    }

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(Md5.toMd5(loginDto.getPassword()));
        response.addHeader("token", "");
        return userService.login(user, response);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto, HttpServletResponse response) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(Md5.toMd5(registerDto.getPassword()));
        user.setName(registerDto.getName());
        return userService.register(user, response);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletResponse response) {
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setHeader("token", "empty");
        return Result.success();
    }

    @PostMapping("/list")
    public Result list(@RequestBody QueryDto queryDto) {
        return userService.queryList(queryDto);
    }
}
