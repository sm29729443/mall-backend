package com.tong.mallbackend.dto;

import com.tong.mallbackend.models.OrderDetailEntity;
import com.tong.mallbackend.models.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ClassName: OrderItem
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class OrderItem {
    private OrderEntity orderEntity;
    private List<OrderDetailEntity> orderDetailEntityList;
}
