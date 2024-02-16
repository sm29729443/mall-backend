package com.tong.mallbackend.controller;

import com.tong.mallbackend.constants.ErrorTypeName;
import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.models.UserEntity;
import com.tong.mallbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * ClassName: UserController
 * Package: com.tong.mallbackend.controller
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 會員註冊
    // Swagger 設定
    @Operation(summary = "會員註冊功能", description = "一個信箱只能註冊一遍",
            // 設定 Swagger requestBody
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "測試", required = true, content = @Content(schema = @Schema(implementation = UserRegisterRequest.class))),
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "201", description = "會員帳號創建成功",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = UserEntity.class))),
                    @ApiResponse(responseCode = "400", description = "request 參數錯誤，可能由以下原因產生：\n"
                                                                      + "- 姓名不得為空\n"
                                                                      + "- 密碼長度不得小於 8 或超過 20\n"
                                                                      + "- 密碼不得包含空白符號\n"
                                                                      + "- 信箱格式錯誤\n"
                                                                      + "- 两次密碼输入不一致\n"
                                                                      + "- 信箱已被註冊",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PostMapping(value = "/public/users/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getRepeatPassword())) {
            log.warn("兩次密碼輸入不一致: password = {}, repeatPassword = {}", request.getPassword(), request.getRepeatPassword());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseBody(String.valueOf(ErrorTypeName.PASSWORD_COMPARE_ERROR), "兩次密碼輸入不一致"));
        }
        UserEntity userEntity = userService.register(request);
        log.info("會員建立成功");
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    // 會員登入
    // 會員登出
    // 會員密碼修改
    // 會員交易平台點數儲值
    // 會員交易平台點數扣款

}
