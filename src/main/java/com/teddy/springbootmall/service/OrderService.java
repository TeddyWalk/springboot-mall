package com.teddy.springbootmall.service;

import com.teddy.springbootmall.model.OrderDetails;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderDetails orderDetails);

    List<OrderDetails> getOrders(Integer userId);

    OrderDetails getOrderById(Integer orderId);
}
