package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CartDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface CartDao extends JpaRepository<CartEntity, Integer> {

    @Modifying
    @Query("UPDATE CartEntity SET totalAmount = :amount WHERE cartId = :cartId")
    void updateAmountByCartId(@Param(value = "amount") Integer amount,
                              @Param(value = "cartId") Integer cartId);
}
