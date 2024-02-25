package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: OrderDetailDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetailEntity, Integer> {
    @Query(value = "SELECT SUM(amount) AS totalAmount FROM order_detail WHERE order_id = :orderId",nativeQuery = true)
    Integer findTotalAmountByOrderId(@Param(value = "orderId") Integer orderId);

    List<OrderDetailEntity> findOrderDetailEntitiesByOrderId(Integer orderId);

    void deleteByOrderId(Integer orderId);
}
