package com.tong.mallbackend.controller;

import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.dto.ProductQueryParams;
import com.tong.mallbackend.dto.ProductRequest;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.models.ProductEntity;
import com.tong.mallbackend.models.UserEntity;
import com.tong.mallbackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductController
 * Package: com.tong.mallbackend.controller
 */
@Slf4j
@Validated
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    // 新增商品
    @Operation(summary = "新增商品功能", description = "登入後賣家id從session拿",
            // 設定 Swagger requestBody
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "測試", required = true, content = @Content(schema = @Schema(implementation = ProductRequest.class))),
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "201", description = "商品創建成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = ProductEntity.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PostMapping("/products")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody @Valid ProductRequest request,
                                                       HttpSession session) {
        Integer sellerId = (Integer) session.getAttribute("userId");
        ProductEntity product = productService.createProduct(request, sellerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // 查詢商品列表
    @Operation(summary = "查詢商品列表功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "查詢商品成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Page.class)))}
    )
    @GetMapping("/public/products")
    public ResponseEntity<Page<ProductEntity>> getProducts(
            // 查詢條件
            @Parameter(description = "用於搜尋商品名稱，為模糊搜尋") @RequestParam(defaultValue = "") String searchName,
            @Parameter(description = "使用商品種類來搜尋商品") @RequestParam(required = false) List<Integer> searchProductCategory,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "金額不得小於0")  Integer searchMinPrice,
            @RequestParam(defaultValue = "9999999") @Max(9999999) Integer searchMaxPrice,
            // 排序條件
            @Parameter(description = "商品列表的排序根據此變數決定，默認根據商品建立時間排序") @RequestParam(defaultValue = "created_time") String orderBy,
            @RequestParam(defaultValue = "DESC") String sort,
            // 分頁條件
            @Parameter(description = "要查詢的頁數，起始頁為 0")@RequestParam(defaultValue = "0") @Min(value = 0, message = "查詢頁不得小於 0") Integer page,
            @Parameter(description = "每頁的商品筆數")@RequestParam(defaultValue = "10") @Max(value = 1000, message = "每頁資料數不得大於 1000") Integer size
            ) {
        ProductQueryParams params = new ProductQueryParams();
        params.setSearchName(searchName);
        params.setSearchProductCategory(searchProductCategory);
        params.setSearchMinPrice(searchMinPrice);
        params.setSearchMaxPrice(searchMaxPrice);
        params.setOrderBy(orderBy);
        params.setSort(sort);
        params.setPage(page);
        params.setSize(size);
        Page<ProductEntity> productEntityList = productService.getProducts(params);
        return ResponseEntity.status(HttpStatus.OK).body(productEntityList);

    }

    // 編輯商品
    @Operation(summary = "編輯商品功能",
            // 設定 Swagger requestBody
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "測試", required = true, content = @Content(schema = @Schema(implementation = ProductRequest.class))),
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "商品編輯成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = ProductEntity.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "403", description = "當嘗試編輯不是自己創建的商品時，返回此 Status",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Integer productId,
                                                       @RequestBody ProductRequest params,
                                                       HttpSession session) {
        ProductEntity productEntity = productService.updateProduct(productId, params, session);
        return ResponseEntity.status(HttpStatus.OK).body(productEntity);
    }

    // 刪除商品
    @Operation(summary = "刪除商品功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "商品刪除成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = ProductEntity.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "403", description = "當嘗試刪除不是自己創建的商品時，返回此 Status",
                            content = @Content(mediaType = "application/json"))}
    )
    @DeleteMapping("products/{productId}")
    public ResponseEntity<ProductEntity> deleteProduct(@PathVariable Integer productId,
                                                       HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Optional<ProductEntity> product = productService.getProduct(userId);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        if (product.get().getSellerId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
