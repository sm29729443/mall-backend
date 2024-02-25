package com.tong.mallbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: OrderDetailEntity
 * Package: com.tong.mallbackend.model
 */
@Setter
@Getter
@Entity
@Table(name = "order_detail", schema = "mall_tong")
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_id")
    private int orderDetailId;
    @Basic
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "amount")
    private int amount;

}
