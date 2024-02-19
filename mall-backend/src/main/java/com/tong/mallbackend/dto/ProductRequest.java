package com.tong.mallbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * ClassName: ProductRequest
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class ProductRequest {

    @NotBlank(message = "請輸入商品名稱")
    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "description")
    String description;

    @NotNull(message = "請輸入商品價格")
    @JsonProperty(value = "price")
    @Min(value = 0, message = "商品價格不得小於 0 ")
    @Max(value = 99999, message = "商品價格不得大於 99999")
    Integer price;

    @NotEmpty(message = "請選擇商品類型")
    @JsonProperty(value = "categoryId")
    Integer[] categoryId;

    @NotNull(message = "請輸入商品庫存數")
    @JsonProperty(value = "stock")
    Integer stock;

    @JsonProperty(value = "imageUrl")
    String imageUrl;


}
