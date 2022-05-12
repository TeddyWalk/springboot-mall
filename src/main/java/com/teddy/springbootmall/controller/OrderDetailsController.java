package com.teddy.springbootmall.controller;

import com.teddy.springbootmall.model.Cart;
import com.teddy.springbootmall.model.OrderDetails;
import com.teddy.springbootmall.model.User;
import com.teddy.springbootmall.service.CartService;
import com.teddy.springbootmall.service.OrderService;
import com.teddy.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/orders/{userId}")
    public ResponseEntity<OrderDetails> createOrder(@PathVariable Integer userId){

        List<Cart> cartList = cartService.getCartByUserId(userId);
        User user = userService.getUserById(userId);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUserId(userId);
        orderDetails.setUserName(user.getName());
        orderDetails.setAddress(user.getAddress());
        orderDetails.setPhone(user.getPhone());

        String productId = "";
        for(int i = 0;i < cartList.size();i++) {
            if(productId.equals("")){
                productId = productId + cartList.get(i).getProductId();
            }else{
                productId = productId + "," + cartList.get(i).getProductId();
            }
        }
        orderDetails.setProductId(productId);

        String productName = "";
        for(int i = 0;i < cartList.size();i++) {
            if(productName.equals("")){
                productName = productName + cartList.get(i).getProductName();
            }else{
                productName = productName + "," + cartList.get(i).getProductName();
            }
        }
        orderDetails.setProductName(productName);

        int totalPrice = 0;
        for(int i = 0;i < cartList.size();i++) {
            totalPrice = totalPrice + cartList.get(i).getSubTotal();
        }
        orderDetails.setTotal(totalPrice);

        Integer orderId = orderService.createOrder(orderDetails);

        OrderDetails odD = orderService.getOrderById(orderId);

        if(odD == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(odD);

    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDetails>> getOrders(@RequestParam Integer userId){

        List<OrderDetails> orderList = orderService.getOrders(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetails> getOrderById(@PathVariable Integer orderId){

        OrderDetails orderDetails = orderService.getOrderById(orderId);

        if(orderDetails == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDetails);
    }
}
