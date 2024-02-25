package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: OrderDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findOrderEntitiesByBuyerId(Integer buyerId);

    Optional<OrderEntity> findByBuyerId(Integer buyerId);
}
