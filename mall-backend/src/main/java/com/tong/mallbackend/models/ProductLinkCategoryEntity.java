package com.tong.mallbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: ProductLinkCategoryEntity
 * Package: com.tong.mallbackend.model
 */
@Setter
@Getter
@Entity
@Table(name = "product_link_category", schema = "mall_tong")
public class ProductLinkCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "category_id", nullable = false)
    private int categoryId;
}
