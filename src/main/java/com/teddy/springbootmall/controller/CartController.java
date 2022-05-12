package com.teddy.springbootmall.controller;

import com.teddy.springbootmall.model.Cart;
import com.teddy.springbootmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart_add")
    public ResponseEntity<String> addProduct(HttpSession session, @RequestBody Cart cart){

        //  把 Session 中的 userId 設定進 Cart
        Integer userId = (Integer) session.getAttribute("userId");
        cart.setUserId(userId);

        // 先確認 Cart 中,是否已存在要 add 的商品了,如果有,就把數量加上去
        List<Cart> cartList = cartService.getCartByUserId(userId);
        if(cartList.size() > 0) {
            for (int i = 0; i < cartList.size(); i++) {
                if (cart.getProductId().equals(cartList.get(i).getProductId())) {
                    Integer quantity = cart.getQuantity() + cartList.get(i).getQuantity();
                    cart.setQuantity(quantity);
                    String msg = cartService.updateCartQuantity(cart);

                    return ResponseEntity.status(HttpStatus.OK).body(msg);
                }
            }
        }

        String msg = cartService.createCart(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Integer userId){

        List<Cart> cartList = cartService.getCartByUserId(userId);

        if(!(cartList.size() > 0)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @PutMapping("/cart/{userId}")
    public ResponseEntity<String> updateCart(@PathVariable Integer userId, @RequestBody Cart cart){
        cart.setUserId(userId);
        String msg = cartService.updateCartQuantity(cart);

        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    // 刪除購物車商品，可以指定某商品 或 全刪
    @DeleteMapping("/cart/{userId}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer userId,
                                             @RequestParam(required = false) Integer productId){
        String msg = cartService.deleteCart(userId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }


}
