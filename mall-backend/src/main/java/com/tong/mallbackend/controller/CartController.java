package com.tong.mallbackend.controller;

import com.tong.mallbackend.dto.CartDetailRequest;
import com.tong.mallbackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CartController
 * Package: com.tong.mallbackend.controller
 */
@Slf4j
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    // 新增商品至購物車
    @PostMapping("/users/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDetailRequest request) {

    }

    //移除商品至購物車

    //查看購物車


}
