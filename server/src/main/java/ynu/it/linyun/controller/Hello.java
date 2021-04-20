package ynu.it.linyun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyun
 * @create 2021-04-20-17:01
 */

@RestController
@RequestMapping(value = "/")
public class Hello {
    @RequestMapping("")
    public String HelloWorld() {
        return "Hello world!";
    }
}
