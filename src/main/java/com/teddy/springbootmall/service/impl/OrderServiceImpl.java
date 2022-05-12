package com.teddy.springbootmall.service.impl;

import com.teddy.springbootmall.dao.OrderDao;
import com.teddy.springbootmall.model.OrderDetails;
import com.teddy.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer createOrder(OrderDetails orderDetails) {
        return orderDao.createOrder(orderDetails);
    }

    @Override
    public List<OrderDetails> getOrders(Integer userId) {
        return orderDao.getOrders(userId);
    }

    @Override
    public OrderDetails getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }
}
