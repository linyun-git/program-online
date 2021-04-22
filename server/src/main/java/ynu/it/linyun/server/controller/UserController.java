package ynu.it.linyun.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ynu.it.linyun.server.common.dto.LoginDto;
import ynu.it.linyun.server.common.dto.RegisterDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.common.util.Md5;
import ynu.it.linyun.server.entity.User;
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

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(Md5.toMd5(loginDto.getPassword()));
        return userService.login(user);
    }

    @PostMapping("register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(Md5.toMd5(registerDto.getPassword()));
        user.setName(registerDto.getName());
        return userService.register(user);
    }
}
