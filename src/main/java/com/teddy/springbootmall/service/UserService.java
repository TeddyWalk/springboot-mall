package com.teddy.springbootmall.service;

import com.teddy.springbootmall.dto.UserLogin;
import com.teddy.springbootmall.model.User;

public interface UserService {

    User getUserByName(User user);

    User getUserById(Integer userId);

    Integer createUser(User user);
}
