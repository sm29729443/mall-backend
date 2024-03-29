package com.tong.mallbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ClassName: CartDetailRequest
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class CartDetailRequest {
    @NotNull(message = "productId 為空")
    @JsonProperty(value = "productId", required = true)
    Integer productId;
    @NotNull(message = "請輸入商品數量")
    @Min(value = 1, message = "購物數量不得小於 1")
    @JsonProperty(value = "quantity", required = true)
    Integer quantity;


}
