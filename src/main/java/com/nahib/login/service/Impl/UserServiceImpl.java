package com.nahib.login.service.Impl;

import com.alibaba.fastjson.JSON;
import com.nahib.login.dao.UserMapper;
import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
import com.nahib.login.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String name) {
        User user = userMapper.selectUser(name);
        return user;
    }

    @Override
    public void insertUser(User user) {
        String MD5Password = MD5Util.md5Encrypt32Lower(user.getPassward());
        user.setPassward(MD5Password);
        userMapper.insertUser(user);
        System.out.println(JSON.toJSONString(userMapper));
    }
}
