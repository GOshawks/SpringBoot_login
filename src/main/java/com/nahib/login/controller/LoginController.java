package com.nahib.login.controller;

import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    

    @PostMapping(value = "/register")
    public String register(@RequestBody User user) throws Exception {
        if(userService.findByName(user.getUserName()) == null){
            userService.insertUser(user);
            return "注册成功";
        }
        return "用户名已存在";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody User user) throws Exception {
        User user1 = userService.findByName(user.getUserName());
        if (user1 != null){
            if(user1.getPassward().equals(DigestUtils.md5DigestAsHex(user.getPassward()
                    .getBytes(StandardCharsets.UTF_8)))){
                return "登录成功";
            }
        }
        return "用户名或密码错误";
    }
}
