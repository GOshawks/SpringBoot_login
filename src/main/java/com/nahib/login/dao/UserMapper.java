package com.nahib.login.dao;

import com.nahib.login.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    User selectUser(String name);

    void insertUser(User user);






}
