package com.teddy.springbootmall.dao.impl;

import com.teddy.springbootmall.dao.CartDao;
import com.teddy.springbootmall.model.Cart;
import com.teddy.springbootmall.rowmapper.CartRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartDaoImpl implements CartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String createCart(Cart cart) {
        String sql = "INSERT INTO cart (user_id, product_id, product_name, " +
                "price, quantity) VALUES (:userId, :productId, :productName, " +
                ":price, :quantity)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", cart.getUserId());
        map.put("productId", cart.getProductId());
        map.put("productName", cart.getProductName());
        map.put("price", cart.getPrice());
        map.put("quantity", cart.getQuantity());

        namedParameterJdbcTemplate.update(sql, map);

        return "成功加入購物車";
    }

    @Override
    public String updateCartQuantity(Cart cart) {
        String sql = "UPDATE cart SET quantity = :quantity " +
                "WHERE user_id = :userId AND product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", cart.getUserId());
        map.put("productId", cart.getProductId());
        map.put("quantity", cart.getQuantity());

        namedParameterJdbcTemplate.update(sql, map);

        return "商品數量更改成功";
    }

    @Override
    public List<Cart> getCartByUserId(Integer userId) {
        String sql = "SELECT user_id, product_id, product_name, " +
                "price, quantity FROM cart WHERE user_id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<Cart> cartList = namedParameterJdbcTemplate.query(sql, map, new CartRowMapper());

        return cartList;
    }

    @Override
    public String deleteCart(Integer userId, Integer productId) {
        String sql = "DELETE FROM cart WHERE 1=1 AND user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        if(productId != null) {
            sql = sql + " AND product_id = :productId";
            map.put("productId", productId);
        }

        namedParameterJdbcTemplate.update(sql, map);

        return "購物車商品刪除成功";
    }
}
