package com.teddy.springbootmall.service.impl;

import com.teddy.springbootmall.dao.UserDao;
import com.teddy.springbootmall.dto.UserLogin;
import com.teddy.springbootmall.model.User;
import com.teddy.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getUserByName(User user) {
        return userDao.getUserByName(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer createUser(User user) {
        return userDao.createUser(user);
    }
}
