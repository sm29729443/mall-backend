package com.tong.mallbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName: CategoryEntity
 * Package: com.tong.mallbackend.model
 */
@Setter
@Getter
@Entity
@Table(name = "category", schema = "mall_tong")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private int categoryId;
    @Basic
    @Column(name = "category", unique = true, nullable = false)
    private String category;
}
