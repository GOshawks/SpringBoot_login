package com.nahib.login.service.Impl;

import com.nahib.login.dao.UserMapper;
import com.nahib.login.entity.User;
import com.nahib.login.service.UserService;
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
        userMapper.insertUser(user);
    }
}
