package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: ProductDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT DISTINCT p.product_id, p.seller_id, p.name, p.description, p.price, p.stock, p.image_url, p.created_time, p.last_modified_time\n" +
            "FROM product AS p JOIN product_link_category AS plc ON p.product_id = plc.product_id\n" +
            "WHERE plc.category_id IN :searchProductCategory AND p.name LIKE :searchName AND p.price BETWEEN :searchMinPrice AND :searchMaxPrice", nativeQuery = true)
    Page<ProductEntity> getProductEntityListWithsearchProductCategory(Pageable pageable,
                                                                      @Param(value = "searchProductCategory") List<Integer> searchProductCategory,
                                                                      @Param(value = "searchName") String searchName,
                                                                      @Param(value = "searchMinPrice") Integer searchMinPrice,
                                                                      @Param(value = "searchMaxPrice") Integer searchMaxPrice);

    @Query(value = "SELECT DISTINCT p.product_id, p.seller_id, p.name, p.description, p.price, p.stock, p.image_url, p.created_time, p.last_modified_time\n" +
            "FROM product AS p JOIN product_link_category AS plc ON p.product_id = plc.product_id\n" +
            "WHERE p.name LIKE :searchName AND p.price BETWEEN :searchMinPrice AND :searchMaxPrice", nativeQuery = true)
    Page<ProductEntity> getProductEntityList(Pageable pageable,
                                             @Param(value = "searchName") String searchName,
                                             @Param(value = "searchMinPrice") Integer searchMinPrice,
                                             @Param(value = "searchMaxPrice") Integer searchMaxPrice);
}
