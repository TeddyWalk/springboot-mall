package com.teddy.springbootmall.rowmapper;

import com.teddy.springbootmall.model.OrderDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderDetails> {

    @Override
    public OrderDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(resultSet.getInt("order_id"));
        orderDetails.setUserId(resultSet.getInt("user_id"));
        orderDetails.setUserName(resultSet.getString("user_name"));
        orderDetails.setAddress(resultSet.getString("user_address"));
        orderDetails.setPhone(resultSet.getString("user_phone"));
        orderDetails.setProductId(resultSet.getString("product_id"));
        orderDetails.setProductName(resultSet.getString("product_name"));
        orderDetails.setTotal(resultSet.getInt("total_price"));
        orderDetails.setCreatDate(resultSet.getDate("created_date"));

        return orderDetails;
    }
}
