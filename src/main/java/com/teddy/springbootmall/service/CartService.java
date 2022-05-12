package com.teddy.springbootmall.service;

import com.teddy.springbootmall.model.Cart;
import com.teddy.springbootmall.model.User;

import java.util.List;

public interface CartService {

    String createCart(Cart cart);

    String updateCartQuantity(Cart cart);

    List<Cart> getCartByUserId(Integer userId);

    String deleteCart(Integer userId, Integer productId);


}
