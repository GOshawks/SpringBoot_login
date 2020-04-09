package com.nahib.login.service.Impl;

import com.nahib.login.dao.UserMapper;
import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

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
        String MD5Password = DigestUtils.md5DigestAsHex(user.getPassward().getBytes(StandardCharsets.UTF_8));
        user.setPassward(MD5Password);
        userMapper.insertUser(user);
    }
}
