package com.teddy.springbootmall.dao.impl;

import com.teddy.springbootmall.dao.UserDao;
import com.teddy.springbootmall.dto.UserLogin;
import com.teddy.springbootmall.model.User;
import com.teddy.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByName(User user) {
        String sql = "SELECT user_id, user_name, user_password, user_address, " +
                "user_phone FROM user WHERE user_name = :userName";
        Map<String, Object> map = new HashMap<>();
        map.put("userName", user.getName());

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, user_name, user_password, user_address, " +
                "user_phone FROM user WHERE user_id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userlist = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(userlist.size() > 0){
            return userlist.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createUser(User user) {
        String sql = "INSERT INTO user (user_name, user_password, " +
                "user_address, user_phone) VALUES (:userName, :userPassword, " +
                ":userAddress, :userPhone)";
        Map<String, Object> map = new HashMap<>();
        map.put("userName", user.getName());
        map.put("userPassword", user.getPassword());
        map.put("userAddress", user.getAddress());
        map.put("userPhone", user.getPhone());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId = keyHolder.getKey().intValue();

        return userId;
    }
}
