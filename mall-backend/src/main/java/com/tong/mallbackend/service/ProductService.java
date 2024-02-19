package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.ProductQueryParams;
import com.tong.mallbackend.dto.ProductRequest;
import com.tong.mallbackend.models.ProductEntity;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * ClassName: ProductService
 * Package: com.tong.mallbackend.service
 */
public interface ProductService {

    ProductEntity createProduct(ProductRequest request, Integer sellerId);

    Page<ProductEntity> getProducts(ProductQueryParams params);

    Optional<ProductEntity> getProduct(Integer productId);

    ProductEntity updateProduct(Integer productId, ProductRequest params, HttpSession session);

    void deleteProduct(Integer productId);
}
