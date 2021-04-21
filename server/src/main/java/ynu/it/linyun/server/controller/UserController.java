package ynu.it.linyun.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
}
