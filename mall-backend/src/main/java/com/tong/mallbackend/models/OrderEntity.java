package com.tong.mallbackend.models;

import com.tong.mallbackend.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * ClassName: OrderEntity
 * Package: com.tong.mallbackend.model
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`", schema = "mall_tong")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "buyer_id")
    private int buyerId;
    @Basic
    @Column(name = "created_time")
    private ZonedDateTime createdTime;
    @Basic
    @Column(name = "last_modified_time")
    private ZonedDateTime lastModifiedTime;
    @Basic
    @Column(name = "total_amount")
    private int totalAmount;
    @Enumerated(EnumType.STRING)
    @Basic
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
