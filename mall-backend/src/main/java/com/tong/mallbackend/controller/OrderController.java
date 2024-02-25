package com.tong.mallbackend.controller;

import com.tong.mallbackend.constants.OrderStatus;
import com.tong.mallbackend.dao.OrderDao;
import com.tong.mallbackend.dto.OrderDetailRequest;
import com.tong.mallbackend.dto.OrderItem;
import com.tong.mallbackend.models.OrderDetailEntity;
import com.tong.mallbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: OrderController
 * Package: com.tong.mallbackend.controller
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 新增訂單
    // 訂單建立前，一定會經過購物車確認購買商品，所以這裡設計成訂單的建立就只是從購物車表轉移
    @PostMapping("/users/orders")
    public ResponseEntity<Void> createOrder(HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        Integer userId = (Integer) session.getAttribute("userId");
        orderService.createOrder(cartId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 查詢訂單列表
    // 因為訂單的資料量小，先暫定不給查詢條件
    @GetMapping("/users/orders")
    public ResponseEntity<List<OrderItem>> getOrderItems(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<OrderItem> orderItemList = orderService.getOrderItems(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
    }

    // 刪除訂單
    @DeleteMapping("/users/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(HttpSession session,
                                            @PathVariable Integer orderId) {
        Integer userId = (Integer) session.getAttribute("userId");
        orderService.deleteOrder(orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
