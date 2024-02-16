package com.tong.mallbackend.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: ErrorResponseBody
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
// 這 class 用來當作發生 error 時返回給前端的 responsebody
// error 不只包含系統層面的 exception，業務邏輯錯誤等的也都算
public class ErrorResponseBody {
    private String errorMessage;
    private String errorTypeName;

    public ErrorResponseBody(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponseBody(String errorTypeName, String errorMessage) {
        this.errorTypeName = errorTypeName;
        this.errorMessage = errorMessage;
    }
}
