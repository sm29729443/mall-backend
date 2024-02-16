package com.tong.mallbackend.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: ProductLinkCategoryEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "product_link_category", schema = "mall_tong")
public class ProductLinkCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "category_id")
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductLinkCategoryEntity that = (ProductLinkCategoryEntity) o;
        return id == that.id && productId == that.productId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, categoryId);
    }
}
