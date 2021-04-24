package ynu.it.linyun.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyun
 * @since 2021-04-24-14:55
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @RequestMapping("/")
    public String hello() {
        return "<h1>hello world!</h1>";
    }
}
