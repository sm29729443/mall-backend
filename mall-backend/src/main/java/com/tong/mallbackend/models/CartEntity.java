package com.tong.mallbackend.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: CartEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "cart", schema = "mall_tong")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cart_id")
    private int cartId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "total_amount")
    private int totalAmount;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return cartId == that.cartId && userId == that.userId && totalAmount == that.totalAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userId, totalAmount);
    }
}
