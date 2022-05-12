package com.teddy.springbootmall.dao;

import com.teddy.springbootmall.dto.UserLogin;
import com.teddy.springbootmall.model.User;

public interface UserDao {

    User getUserByName(User user);

    User getUserById(Integer userId);

    Integer createUser(User user);
}
