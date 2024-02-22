package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.CartDetailRequest;
import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.models.CartDetailEntity;

import java.util.List;

/**
 * ClassName: CartService
 * Package: com.tong.mallbackend.service
 */
public interface CartService {
    void addProductToCart(CartDetailRequest request, Integer cartId);

    void removeProductFromCart(Integer cartDetailId);

    CartDetailEntity updateProductQuantityFromCart(CartDetailRequest request, Integer cartId);

    List<CartItem> getProductListFromCart(Integer cartId);
}
