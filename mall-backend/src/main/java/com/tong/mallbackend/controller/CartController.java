package com.tong.mallbackend.controller;

import com.tong.mallbackend.dto.CartDetailRequest;
import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.models.CartDetailEntity;
import com.tong.mallbackend.models.ProductEntity;
import com.tong.mallbackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ResponseEntity<?> addProductToCart(@RequestBody CartDetailRequest request,
                                              HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        cartService.addProductToCart(request, cartId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //刪除整個商品至購物車
    @DeleteMapping("/users/cart/{cartDetailId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Integer cartDetailId) {
        cartService.removeProductFromCart(cartDetailId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //編輯購物車裡的商品數量
    @PutMapping("/users/cart/cartDetail")
    public ResponseEntity<?> updateProductQuantityFromCart(@RequestBody CartDetailRequest request,
                                                           HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        CartDetailEntity cartDetailEntity = cartService.updateProductQuantityFromCart(request, cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartDetailEntity);
    }

    //查看購物車
    @GetMapping("/users/cart")
    public ResponseEntity<List<CartItem>> getProductListFromCart(HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        List<CartItem> cartItemList = cartService.getProductListFromCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemList);
    }

}
