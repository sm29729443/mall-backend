package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.ProductLinkCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: ProductLinkCategoryDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface ProductLinkCategoryDao extends JpaRepository<ProductLinkCategoryEntity, Integer> {

}
