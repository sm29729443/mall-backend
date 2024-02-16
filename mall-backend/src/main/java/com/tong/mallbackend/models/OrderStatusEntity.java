package com.tong.mallbackend.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: OrderStatusEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "order_status", schema = "mall_tong")
public class OrderStatusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_status_id")
    private int orderStatusId;
    @Basic
    @Column(name = "order_status")
    private String orderStatus;

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatusEntity that = (OrderStatusEntity) o;
        return orderStatusId == that.orderStatusId && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatusId, orderStatus);
    }
}
