package com.tong.mallbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ClassName: ProductQueryParams
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class ProductQueryParams {
    private String searchName;
    private List<Integer> searchProductCategory;
    private Integer searchMinPrice;
    private Integer searchMaxPrice;
    private String orderBy;
    private String sort;
    private Integer page;
    private Integer size;

}
