package com.teddy.springbootmall.dao.impl;

import com.teddy.springbootmall.dao.OrderDao;
import com.teddy.springbootmall.model.OrderDetails;
import com.teddy.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(OrderDetails orderDetails) {
        String sql = "INSERT INTO order_details (user_id, user_name, user_address, " +
                "user_phone, product_id, product_name, total_price, created_date) " +
                "VALUES (:userId, :userName, :userAddress, :userPhone, :productId, " +
                ":productName, :totalPrice, :createDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", orderDetails.getUserId());
        map.put("userName", orderDetails.getUserName());
        map.put("userAddress", orderDetails.getAddress());
        map.put("userPhone", orderDetails.getPhone());
        map.put("productId", orderDetails.getProductId());
        map.put("productName", orderDetails.getProductName());
        map.put("totalPrice", orderDetails.getTotal());

        Date now = new Date();
        map.put("createDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();
        System.out.println("新增訂單的 orderId 為 "+orderId);

        return orderId;
    }

    @Override
    public List<OrderDetails> getOrders(Integer userId) {
        String sql = "SELECT order_id, user_id, user_name, user_address, " +
                "user_phone, product_id, product_name, total_price, " +
                "created_date FROM order_details WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<OrderDetails> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList;
    }

    @Override
    public OrderDetails getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id, user_name, user_address, " +
                "user_phone, product_id, product_name, total_price, " +
                "created_date FROM order_details WHERE order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderDetails> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if(orderList.size() > 0){
            return orderList.get(0);
        }else{
            return null;
        }
    }
}
