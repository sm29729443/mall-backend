package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.dao.ProductCategoryDao;
import com.tong.mallbackend.dto.ProductCategoryRequest;
import com.tong.mallbackend.exceptions.ProductCategoryException;
import com.tong.mallbackend.models.CategoryEntity;
import com.tong.mallbackend.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductCategoryServiceImpl
 * Package: com.tong.mallbackend.service.Impl
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public Optional<CategoryEntity> findCategoryById(Integer categoryId) {
        return productCategoryDao.findById(categoryId);
    }

    @Override
    public Optional<CategoryEntity> findCategoryByCategory(String category) {
        return productCategoryDao.findByCategory(category);

    }

    @Override
    public CategoryEntity createProductCategory(String category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if (productCategoryDao.findByCategory(category).isPresent()) {
            throw new ProductCategoryException("此商品種類已存在", HttpStatus.CONFLICT);
        }
        categoryEntity.setCategory(category);
        return productCategoryDao.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> getProductCategorys(String category) {
        List<CategoryEntity> productCategorysList = productCategoryDao.findByCategoryLikeIgnoreCaseOrderByCategoryId("%" + category + "%");
        return productCategorysList;
    }

    @Override
    public void deleteProductCategory(Integer categoryId) {
        productCategoryDao.deleteById(categoryId);
    }

    @Override
    public CategoryEntity updateProductCategory(Integer categoryId, ProductCategoryRequest request) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryId);
        categoryEntity.setCategory(request.getCategory());
        return productCategoryDao.save(categoryEntity);
    }


}
