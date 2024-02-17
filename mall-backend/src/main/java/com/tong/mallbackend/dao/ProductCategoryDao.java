package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.CategoryEntity;
import com.tong.mallbackend.models.ProductLinkCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductCategoryDao
 * Package: com.tong.mallbackend.dao
 */
public interface ProductCategoryDao extends JpaRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByCategory(String category);

    List<CategoryEntity> findByCategoryLikeIgnoreCaseOrderByCategoryId(String category);
}
