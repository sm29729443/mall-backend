package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.OrderItem;
import com.tong.mallbackend.models.OrderEntity;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: OrderService
 * Package: com.tong.mallbackend.service
 */
public interface OrderService {
    void createOrder(Integer cartId, Integer userId);

    List<OrderItem> getOrderItems(Integer userId);

    void deleteOrder(Integer orderId, Integer userId);

    Optional<OrderEntity> getOrderByUserId(Integer userId);
}
