package com.teddy.springbootmall.dao;

import com.teddy.springbootmall.model.Cart;

import java.util.List;

public interface CartDao {

    String createCart(Cart cart);

    String updateCartQuantity(Cart cart);

    List<Cart> getCartByUserId(Integer userId);

    String deleteCart(Integer userId, Integer productId);
}
