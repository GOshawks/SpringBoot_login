package com.nahib.login.service;

import com.nahib.login.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    //通过name查找用户
    User findByName(String name) throws Exception;

    //插入一条user信息
    void insertUser(User user) throws Exception;

}
