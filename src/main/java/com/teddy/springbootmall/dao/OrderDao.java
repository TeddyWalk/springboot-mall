package com.teddy.springbootmall.dao;

import com.teddy.springbootmall.model.OrderDetails;

import java.util.List;

public interface OrderDao {

    Integer createOrder(OrderDetails orderDetails);

    List<OrderDetails> getOrders(Integer userId);

    OrderDetails getOrderById(Integer orderId);
}
