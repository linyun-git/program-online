package ynu.it.linyun.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/hello")
    public String getUserInfo(){
        return "Hello world!";
    }
}
