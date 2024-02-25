package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.constants.OrderStatus;
import com.tong.mallbackend.dao.*;
import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.dto.OrderItem;
import com.tong.mallbackend.models.OrderDetailEntity;
import com.tong.mallbackend.models.OrderEntity;
import com.tong.mallbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: OrderServiceImpl
 * Package: com.tong.mallbackend.service.Impl
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartDetailDao cartDetailDao;
    @Autowired
    private ProductDao productDao;
    @Transactional
    @Override
    public void createOrder(Integer cartId, Integer userId) {
        // 創建 Order table 資料
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        orderEntity.setCreatedTime(now);
        orderEntity.setLastModifiedTime(now);
        orderEntity.setBuyerId(userId);
        OrderEntity order = orderDao.save(orderEntity);

        // 創建 OrderDetail table 資料
        List<CartItem> cartItemList = cartDetailDao.findByCartId(cartId);
        List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
        cartItemList.forEach(cartItem -> {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderId(order.getOrderId());
            orderDetailEntity.setAmount(cartItem.getItemAmount());
            orderDetailEntity.setQuantity(cartItem.getProductQuantity());
            orderDetailEntity.setProductId(cartItem.getProductId());
            orderDetailEntityList.add(orderDetailEntity);

            // 更新商品庫存
            productDao.updateStockByProductId(cartItem.getProductId(), cartItem.getProductQuantity());
        });
        orderDetailDao.saveAll(orderDetailEntityList);
        order.setTotalAmount(orderDetailDao.findTotalAmountByOrderId(order.getOrderId()));
        // 訂單成立後刪除購物車的資訊
        cartDao.updateAmountByCartId(0, cartId);
        cartDetailDao.deleteCartDetailEntitiesByCartId(cartId);
    }

    @Override
    public List<OrderItem> getOrderItems(Integer userId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        List<OrderEntity> orderEntityList = orderDao.findOrderEntitiesByBuyerId(userId);
        orderEntityList.forEach( orderEntity -> {
            List<OrderDetailEntity> orderDetailEntityList = orderDetailDao.findOrderDetailEntitiesByOrderId(orderEntity.getOrderId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderEntity(orderEntity);
            orderItem.setOrderDetailEntityList(orderDetailEntityList);
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }

    @Override
    public void deleteOrder(Integer orderId, Integer userId) {
        Optional<OrderEntity> orderEntity = orderDao.findById(orderId);
        if (orderEntity.get().getBuyerId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        orderDao.deleteById(orderId);
        orderDetailDao.deleteByOrderId(orderId);
    }

    @Override
    public Optional<OrderEntity> getOrderByUserId(Integer userId) {
        return orderDao.findByBuyerId(userId);
    }
}
