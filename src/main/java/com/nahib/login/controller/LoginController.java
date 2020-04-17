package com.nahib.login.controller;

import com.nahib.login.aop.SignatureValidation;
import com.nahib.login.entity.Result;
import com.nahib.login.entity.ResultEnum;
import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
import com.nahib.login.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SignatureValidation
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    

    @GetMapping(value = "/register")

    public String register(User user) throws Exception {
        String userName = user.getUserName();
        if(!(StringUtils.isNoneBlank(userName,user.getPassward()))){
            if(userService.findByName(user.getUserName()) == null){
                userService.insertUser(user);
                return "注册成功";
            }
        }
        return "用户名已存在";
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String,String> mapParam) throws Exception {
        ResultUtil result = new ResultUtil();
        Map map = new HashMap();

        String userName = mapParam.get("userName");//从前端获取用户名
        String pwd = mapParam.get("passward");//从前端获取密码
        User user1 = userService.findByName(userName);
        if (user1 != null){
            if(user1.getPassward().equals(MD5Util.md5Encrypt32Lower(pwd))){
                //用户验证成功，生成token
                String token = JwtUtils.createToken(userName);
                map.put("token",token);
                return result.success(ResultEnum.LOGIN_SUCCESS,map);
            }
        }
        return result.error(ResultEnum.NAME_OR_PWD_ERROR);
    }

    @PostMapping(value = "/getUser")
    public Result getUser(HttpServletRequest request, @RequestBody Map<String, String> mapParam) throws Exception {
        ResultUtil result = new ResultUtil();
        Map map = new HashMap();

        //从http头里获取token信息
        String token = request.getHeader("token");
        String timestamp = request.getHeader("timestamp");
        System.out.println(timestamp);
        System.out.println(token);
        String userName = mapParam.get("userName");//从前端获取用户名
        String pwdRSA = mapParam.get("passward");//从前端获取RSA加密密码
        //获取私钥
         String key = mapParam.get("privateKey");
        //RSA解密
        String pwd = null;

        try{
            pwd = RSAUtils.decrypt(pwdRSA,key);
        }catch (Exception e){
            return result.error(ResultEnum.RSA_KEY_ERROR);
        }
        //验证token
        boolean verifyToken = JwtUtils.verifyToken(token);
        if(!verifyToken){
            return result.error(ResultEnum.TOKEN_VERIFY_ERROR);//如果token验证失败直接返回错误信息
        }
        User userParam = userService.findByName(userName);
        if (userParam != null) {
            if (userParam.getPassward().equals(MD5Util.md5Encrypt32Lower(pwd))) {

                map.put("user", userParam);
                return result.success(ResultEnum.FIND_USER_SUCCESS, map);
            }
        }
        return result.error(ResultEnum.FIND_USER_ERROR);
    }
}
