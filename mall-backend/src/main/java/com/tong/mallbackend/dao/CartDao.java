package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CartDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface CartDao extends JpaRepository<CartEntity, Integer> {
}
