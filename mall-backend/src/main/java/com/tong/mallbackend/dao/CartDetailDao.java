package com.tong.mallbackend.dao;

import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.models.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: CartDetailDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface CartDetailDao extends JpaRepository<CartDetailEntity, Integer> {
    Optional<CartDetailEntity> findByProductId(Integer productId);
    Optional<CartDetailEntity> findByCartIdAndProductId(Integer cartId, Integer productId);

    @Query("SELECT new com.tong.mallbackend.dto.CartItem(p.productId, p.name, p.description, p.price, c.quantity, c.amount) " +
            "FROM ProductEntity AS p JOIN CartDetailEntity AS c ON p.productId = c.productId " +
            "WHERE c.cartId = :cartId")
    List<CartItem> findByCartId(Integer cartId);

    @Query(value = "SELECT SUM(amount) AS totalAmount FROM cart_detail WHERE cart_id = :cartId",nativeQuery = true)
    Integer findTotalAmountByCartId(@Param(value = "cartId") Integer cartId);

    void deleteCartDetailEntitiesByCartId(Integer cartId);
}
