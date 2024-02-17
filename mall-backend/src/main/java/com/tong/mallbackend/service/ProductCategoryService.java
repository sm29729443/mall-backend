package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.ProductCategoryRequest;
import com.tong.mallbackend.models.CategoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductCategoryService
 * Package: com.tong.mallbackend.service
 */
public interface ProductCategoryService {

    Optional<CategoryEntity> findCategoryById(Integer categoryId);

    Optional<CategoryEntity> findCategoryByCategory(String category);
    CategoryEntity createProductCategory(String category);

    List<CategoryEntity> getProductCategorys(String category);

    void deleteProductCategory(Integer categoryId);

    CategoryEntity updateProductCategory(Integer categoryId, ProductCategoryRequest request);


}
