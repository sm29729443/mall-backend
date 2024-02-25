package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.dao.CartDao;
import com.tong.mallbackend.dao.CartDetailDao;
import com.tong.mallbackend.dao.ProductDao;
import com.tong.mallbackend.dto.CartDetailRequest;
import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.exceptions.CartException;
import com.tong.mallbackend.exceptions.ProductException;
import com.tong.mallbackend.models.CartDetailEntity;
import com.tong.mallbackend.models.CartEntity;
import com.tong.mallbackend.models.ProductEntity;
import com.tong.mallbackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: CartServiceImpl
 * Package: com.tong.mallbackend.service.Impl
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartDetailDao cartDetailDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public void addProductToCart(CartDetailRequest request, Integer cartId) {
        Optional<ProductEntity> productEntity = productDao.findById(request.getProductId());
        if (productEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "此商品不存在");
        }
        ProductEntity product = productEntity.get();
        if (product.getStock() < request.getQuantity()) {
            throw new ProductException("商品庫存不足", HttpStatus.BAD_REQUEST);
        }
        Optional<CartDetailEntity> detailEntity = cartDetailDao.findByProductId(request.getProductId());
        CartDetailEntity cartDetailEntity;
        if (detailEntity.isEmpty()) {
            cartDetailEntity = new CartDetailEntity();
            cartDetailEntity.setCartId(cartId);
            cartDetailEntity.setProductId(request.getProductId());
            cartDetailEntity.setQuantity(request.getQuantity());
            cartDetailEntity.setAmount(product.getPrice() * request.getQuantity());
        } else {
            cartDetailEntity = detailEntity.get();
            Integer quantity = cartDetailEntity.getQuantity() + request.getQuantity();
            cartDetailEntity.setQuantity(quantity);
            cartDetailEntity.setAmount(quantity * product.getPrice());
        }
        cartDetailDao.save(cartDetailEntity);
        CartEntity cartEntity = cartDao.findById(cartId).get();
        cartEntity.setTotalAmount(cartDetailDao.findTotalAmountByCartId(cartId));

    }

    @Override
    public void removeProductFromCart(Integer cartDetailId) {
        cartDetailDao.deleteById(cartDetailId);
    }

    @Transactional
    @Override
    public CartDetailEntity updateProductQuantityFromCart(CartDetailRequest request, Integer cartId) {
        Boolean success = false;
        CartDetailEntity detailEntity = null;
        while (!success) {
            try {
                Optional<ProductEntity> productEntity = productDao.findById(request.getProductId());
                if (productEntity.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "此商品不存在");
                }
                ProductEntity product = productEntity.get();
                if (product.getStock() < request.getQuantity()) {
                    throw new ProductException("商品庫存不足", HttpStatus.BAD_REQUEST);
                }

                Optional<CartDetailEntity> cartDetailEntity = cartDetailDao.findByCartIdAndProductId(cartId, request.getProductId());
                if (cartDetailEntity.isEmpty()) {
                    throw new CartException("此商品不存在於您的購物車", HttpStatus.NOT_FOUND);
                }
                CartDetailEntity cartDetail = cartDetailEntity.get();
                cartDetail.setQuantity(request.getQuantity());
                cartDetail.setAmount(request.getQuantity() * product.getPrice());
                detailEntity = cartDetailDao.save(cartDetail);
                productDao.save(product);
                success = true;
            } catch (OptimisticEntityLockException exception) {
                log.warn("錯誤訊息，發生於 updateProductQuantityFromCart Method :{}", exception.getMessage());
            }
        }
        return detailEntity;
    }

    @Override
    public List<CartItem> getProductListFromCart(Integer cartId) {
        List<CartItem> cartDetailEntityList = cartDetailDao.findByCartId(cartId);
        return cartDetailEntityList;
    }
}
