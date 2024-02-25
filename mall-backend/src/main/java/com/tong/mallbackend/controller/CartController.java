package com.tong.mallbackend.controller;

import com.tong.mallbackend.dto.CartDetailRequest;
import com.tong.mallbackend.dto.CartItem;
import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.models.CartDetailEntity;
import com.tong.mallbackend.models.CategoryEntity;
import com.tong.mallbackend.models.ProductEntity;
import com.tong.mallbackend.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: CartController
 * Package: com.tong.mallbackend.controller
 */
@Slf4j
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    // 新增商品至購物車
    @Operation(summary = "新增商品至購物車功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "201", description = "商品放入購物車成功",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "商品庫存不足",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "404", description = "商品不存在",
                            content = @Content(mediaType = "application/json"))}
    )
    @PostMapping("/users/cart")
    public ResponseEntity<Void> addProductToCart(@RequestBody @Valid CartDetailRequest request,
                                              HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        cartService.addProductToCart(request, cartId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //刪除整個商品至購物車
    @Operation(summary = "刪除購物車裡的商品功能，不是更新數量，是直接將此商品移除",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "商品刪除成功",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))
            }
    )
    @DeleteMapping("/users/cart/{cartDetailId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Integer cartDetailId) {
        cartService.removeProductFromCart(cartDetailId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //編輯購物車裡的商品數量
    @Operation(summary = "編輯購物車裡的商品數量功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "商品數量編輯成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = CartDetailEntity.class))),
                    @ApiResponse(responseCode = "400", description = "參數錯誤或商品庫存不足",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "404", description = "商品不存在或於購物車裡不存在",
                            content = @Content(mediaType = "application/json"))}
    )
    @PutMapping("/users/cart/cartDetail")
    public ResponseEntity<CartDetailEntity> updateProductQuantityFromCart(@RequestBody @Valid CartDetailRequest request,
                                                           HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        CartDetailEntity cartDetailEntity = cartService.updateProductQuantityFromCart(request, cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartDetailEntity);
    }

    //查看購物車
    @Operation(summary = "查看購物車功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "查詢成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CartItem.class))),
            }
    )
    @GetMapping("/users/cart")
    public ResponseEntity<List<CartItem>> getProductListFromCart(HttpSession session) {
        Integer cartId = (Integer) session.getAttribute("cartId");
        List<CartItem> cartItemList = cartService.getProductListFromCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemList);
    }

}
