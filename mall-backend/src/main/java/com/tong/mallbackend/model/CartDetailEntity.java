package com.tong.mallbackend.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: CartDetailEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "cart_detail", schema = "mall_tong")
public class CartDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cart_detail_id")
    private int cartDetailId;
    @Basic
    @Column(name = "cart_id")
    private int cartId;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "amount")
    private int amount;

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetailEntity that = (CartDetailEntity) o;
        return cartDetailId == that.cartDetailId && cartId == that.cartId && productId == that.productId && quantity == that.quantity && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartDetailId, cartId, productId, quantity, amount);
    }
}
