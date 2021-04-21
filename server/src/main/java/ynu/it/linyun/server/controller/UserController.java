package ynu.it.linyun.server.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author linyun
 * @create 2021-04-20-22:50
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/query")
    public String queryUser(@RequestParam(value = "id") Integer id){
        User user = userService.selectUser(id);
        if (user == null) {
            return "user not found";
        }
        return user.toString();
    }
}
