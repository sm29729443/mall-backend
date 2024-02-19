package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.dao.ProductDao;
import com.tong.mallbackend.dao.ProductLinkCategoryDao;
import com.tong.mallbackend.dto.ProductQueryParams;
import com.tong.mallbackend.dto.ProductRequest;
import com.tong.mallbackend.models.ProductEntity;
import com.tong.mallbackend.models.ProductLinkCategoryEntity;
import com.tong.mallbackend.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProduceServiceImpl
 * Package: com.tong.mallbackend.service.Impl
 */
@Slf4j
@Service
public class ProduceServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductLinkCategoryDao productLinkCategoryDao;

    @Transactional
    @Override
    public ProductEntity createProduct(ProductRequest request, Integer sellerId) {
        ProductEntity product = new ProductEntity();
        product.setSellerId(sellerId);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        product.setCreatedTime(now);
        product.setLastModifiedTime(now);
        ProductEntity productEntity = productDao.save(product);
        List<ProductLinkCategoryEntity> productLinkCategoryEntityList = new ArrayList<>(request.getCategoryId().length);
        for (int i = 0; i < request.getCategoryId().length; i++) {
            ProductLinkCategoryEntity productLinkCategoryEntity = new ProductLinkCategoryEntity();
            productLinkCategoryEntity.setProductId(productEntity.getProductId());
            productLinkCategoryEntity.setCategoryId(request.getCategoryId()[i]);
            productLinkCategoryEntityList.add(productLinkCategoryEntity);
        }
        List<ProductLinkCategoryEntity> productLinkCategoryEntities = productLinkCategoryDao.saveAll(productLinkCategoryEntityList);

        return productEntity;
    }

    @Override
    public Page<ProductEntity> getProducts(ProductQueryParams params) {
        String searchName = params.getSearchName();
        List<Integer> searchProductCategory = params.getSearchProductCategory();
        Integer searchMinPrice = params.getSearchMinPrice();
        Integer searchMaxPrice = params.getSearchMaxPrice();
        String orderBy = params.getOrderBy();
        String sort = params.getSort();
        Integer page = params.getPage();
        Integer size = params.getSize();
        // Like 模糊搜尋
        String searchNameLike = "%" + searchName + "%";

        //排序
        Sort newSort = Sort.by(Sort.Direction.valueOf(sort), orderBy);
        PageRequest pageRequest = PageRequest.of(page, size, newSort);

        Page<ProductEntity> productEntityList;
        if (searchProductCategory != null) {
            productEntityList = productDao.getProductEntityListWithsearchProductCategory(pageRequest, searchProductCategory, searchNameLike, searchMinPrice, searchMaxPrice);
        } else {
            productEntityList = productDao.getProductEntityList(pageRequest, searchNameLike, searchMinPrice, searchMaxPrice);
        }

        return productEntityList;
    }

    @Override
    public Optional<ProductEntity> getProduct(Integer productId) {
        Optional<ProductEntity> productEntity = productDao.findById(productId);
        if (productEntity.isEmpty()) {
            log.warn("此 productId 於資料庫不存在:{}", productId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "此productId不存在商品");
        }
        return productEntity;
    }

    @Override
    @Transactional
    public ProductEntity updateProduct(Integer productId, ProductRequest params, HttpSession session) {

        Optional<ProductEntity> productEntity = productDao.findById(productId);
        if (productEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "此商品不存在");
        }

        ProductEntity product = productEntity.get();
        if ((Integer) session.getAttribute("userId") != product.getSellerId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "不能修改不是自己創建的商品");
        }

        product.setName(params.getName());
        product.setDescription(params.getDescription());
        product.setPrice(params.getPrice());
        product.setStock(params.getStock());
        product.setImageUrl(params.getImageUrl());
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        product.setLastModifiedTime(now);
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        productDao.deleteById(productId);
    }
}
