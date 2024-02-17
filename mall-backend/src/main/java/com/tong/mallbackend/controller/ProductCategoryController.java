package com.tong.mallbackend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.dto.ProductCategoryRequest;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.models.CategoryEntity;
import com.tong.mallbackend.models.UserEntity;
import com.tong.mallbackend.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: ProductCategoryController
 * Package: com.tong.mallbackend.controller
 */
@RestController
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    //新增商品種類
    @Operation(summary = "新增商品種類功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "201", description = "商品種類創建成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = CategoryEntity.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "409", description = "要新增的商品種類已存在",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PostMapping("/productCategorys")
    public ResponseEntity<CategoryEntity> createProductCategory(@RequestParam @JsonProperty(value = "category") String category) {
        CategoryEntity categoryEntity = productCategoryService.createProductCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryEntity);
    }



    //查詢商品種類
    @Operation(summary = "查詢商品種類功能", description = "模糊查詢，category參數未指定時，默認搜尋全部",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "查詢成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CategoryEntity.class)))}
    )
    @GetMapping("public/productCategorys")
    public ResponseEntity<List<CategoryEntity>> getProductCategorys(@RequestParam(defaultValue = "") String category) {
        List<CategoryEntity> productCategorysList = productCategoryService.getProductCategorys(category);
        return ResponseEntity.status(HttpStatus.OK).body(productCategorysList);
    }

    //刪除商品種類
    @Operation(summary = "刪除商品種類功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "商品種類刪除成功",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "500", description = "categoryId於資料庫不存在，應該只有這種可能",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HttpServletResponse.class)))}
    )
    @DeleteMapping("/productCategorys/{categoryId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable Integer categoryId) {
        productCategoryService.deleteProductCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    //修改商品種類
    @Operation(summary = "修改商品種類功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "編輯商品種類成功",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "404", description = "此商品種類不存在",
                            content = @Content()),
                    @ApiResponse(responseCode = "409", description = "發生於以下example: 香蕉 -> 蘋果，但蘋果已存在於其他資料庫欄位，違反資料庫 Unique")}
    )
    @PutMapping("/productCategorys/{categoryId}")
    public ResponseEntity<CategoryEntity> updateProductCategory(@PathVariable Integer categoryId,
                                                                @RequestBody ProductCategoryRequest request) {
        Optional<CategoryEntity> category = productCategoryService.findCategoryById(categoryId);
        if (category.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<CategoryEntity> categoryTwo = productCategoryService.findCategoryByCategory(request.getCategory());
        if (categoryTwo.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CategoryEntity categoryEntity = productCategoryService.updateProductCategory(categoryId, request);
        return ResponseEntity.status(HttpStatus.OK).body(categoryEntity);
    }

}
