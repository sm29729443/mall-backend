package com.tong.mallbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * ClassName: ProductEntity
 * Package: com.tong.mallbackend.models
 */
@Setter
@Getter
@Entity
@Table(name = "product", schema = "mall_tong")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "seller_id", nullable = false)
    private int sellerId;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @Basic
    @Column(name = "stock", nullable = false)
    private int stock;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @Basic
    @Column(name = "created_time", nullable = false)
    private ZonedDateTime createdTime;
    @Basic
    @Column(name = "last_modified_time", nullable = false)
    private ZonedDateTime lastModifiedTime;

}
