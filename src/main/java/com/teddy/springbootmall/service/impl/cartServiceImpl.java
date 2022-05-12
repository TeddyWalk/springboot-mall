package com.teddy.springbootmall.service.impl;

import com.teddy.springbootmall.dao.CartDao;
import com.teddy.springbootmall.model.Cart;
import com.teddy.springbootmall.model.User;
import com.teddy.springbootmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class cartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public String createCart(Cart cart) {
        return cartDao.createCart(cart);
    }

    @Override
    public String updateCartQuantity(Cart cart) {
        return cartDao.updateCartQuantity(cart);
    }

    @Override
    public List<Cart> getCartByUserId(Integer userId) {
        return cartDao.getCartByUserId(userId);
    }

    @Override
    public String deleteCart(Integer userId, Integer productId) {
        return cartDao.deleteCart(userId, productId);
    }
}
