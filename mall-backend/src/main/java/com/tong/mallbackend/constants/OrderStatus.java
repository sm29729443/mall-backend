package com.tong.mallbackend.constants;

/**
 * ClassName: OrderStatus
 * Package: com.tong.mallbackend.constants
 */
//因交易方式只以 point 模擬，所以只能假設商品出貨需先付款，沒有貨到付款這種功能
public enum OrderStatus {
    // 訂單已建立但尚未付款
    PENDING_PAYMENT("訂單尚未付款"),
    // 訂單已建立
    PROCESSING("商品待出貨"),
    SHIPPED("商品已出貨"),
    DELIVERED("商品已送達"),
    COMPLETED("訂單已完成"),
    CANCELLED("訂單已取消");
    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
