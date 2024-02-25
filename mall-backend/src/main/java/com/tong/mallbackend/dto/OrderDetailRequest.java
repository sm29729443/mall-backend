package com.tong.mallbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: OrderDetailRequest
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class OrderDetailRequest {
    @NotNull(message = "productId not null")
    private Integer productId;
    @NotBlank(message = "請輸入商品數量")
    private Integer quantity;
    @NotBlank(message = "product amount error")
    private Integer amount;
}
