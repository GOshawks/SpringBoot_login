package com.nahib.login.controller;

import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    

    @PostMapping(value = "/register")
    public String register(@RequestBody User user) throws Exception {
        userService.insertUser(user);
        return "注册成功";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody User user) throws Exception {
        User user1 = userService.findByName(user.getUserName());
        if (user1 != null){
            if(user1.getPassward().equals(user.getPassward())){
                return "登录成功";
            }
        }
        return "用户名或密码错误";
    }
}
