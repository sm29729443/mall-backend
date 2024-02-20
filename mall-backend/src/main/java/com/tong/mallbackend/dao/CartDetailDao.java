package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CartDetailDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface CartDetailDao extends JpaRepository<CartDetailEntity, Integer> {
}
