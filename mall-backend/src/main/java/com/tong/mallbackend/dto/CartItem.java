package com.tong.mallbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClassName: CartItem
 * Package: com.tong.mallbackend.dto
 */
@Getter
@Setter
public class CartItem {
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productQuantity;
    private Integer itemAmount;

    public CartItem() {
    }

    public CartItem(Integer productId, String productName, String productDescription, Integer productPrice, Integer productQuantity, Integer itemAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.itemAmount = itemAmount;
    }
}
