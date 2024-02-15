package com.tong.mallbackend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ClassName: OrderEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "order", schema = "mall_tong")
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
    private Timestamp createdTime;
    @Basic
    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;
    @Basic
    @Column(name = "total_amount")
    private int totalAmount;
    @Basic
    @Column(name = "order_status_id")
    private int orderStatusId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderId == that.orderId && buyerId == that.buyerId && totalAmount == that.totalAmount && orderStatusId == that.orderStatusId && Objects.equals(createdTime, that.createdTime) && Objects.equals(lastModifiedTime, that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, buyerId, createdTime, lastModifiedTime, totalAmount, orderStatusId);
    }
}
