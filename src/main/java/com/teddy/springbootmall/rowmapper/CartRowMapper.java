package com.teddy.springbootmall.rowmapper;

import com.teddy.springbootmall.model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart = new Cart();
        cart.setUserId(resultSet.getInt("user_id"));
        cart.setProductId(resultSet.getInt("product_id"));
        cart.setProductName(resultSet.getString("product_name"));
        cart.setPrice(resultSet.getInt("price"));
        cart.setQuantity(resultSet.getInt("quantity"));

        Integer total = cart.getPrice()*cart.getQuantity();
        cart.setSubTotal(total);

        return cart;
    }
}
