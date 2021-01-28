package it.eogroup.controller;

import it.eogroup.domain.User;
import it.eogroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author glx
 * @date 2021/01/27/15:18
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user) {
        System.out.println(user);
        boolean b = userService.isLogin(user);
        if (b) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping("/register")
    public String register(User user) {
        System.out.println(user);
        Boolean b = userService.isRegister(user);
        if (b) {
            return "success";
        } else {
            return "failed";
        }
    }
}
