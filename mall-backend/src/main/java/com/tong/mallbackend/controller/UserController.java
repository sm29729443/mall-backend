package com.tong.mallbackend.controller;

import com.tong.mallbackend.constants.ErrorTypeName;
import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.dto.UserLoginRequest;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.models.UserEntity;
import com.tong.mallbackend.service.UserService;
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
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserRegisterRequest.class))),
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
    @Operation(summary = "會員登入功能",
            // 設定 Swagger requestBody
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserLoginRequest.class))),
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "會員登入成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = UserEntity.class))),
                    @ApiResponse(responseCode = "400", description = "email 或 password 參數錯誤，可能由以下原因產生：\n"
                            + "- email 為空\n"
                            + "- password 為空\n",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "404", description = "email 或 password 與資料庫不一，可能由以下原因產生：\n"
                            + "- email 不存在\n"
                            + "- email 存在但 password 錯誤\n",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PostMapping("/public/users/login")
    public ResponseEntity<UserEntity> login(@RequestBody @Valid UserLoginRequest request,
                                            HttpSession session) {
        UserEntity user = userService.login(request, session);
        log.info("登入成功 email:{}", request.getEmail());
        log.info("session email:{}", session.getAttribute("email"));
        log.info("session userName:{}", session.getAttribute("userName"));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 會員登出
    @Operation(summary = "會員登出功能",
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "會員登出成功，不返回任何資訊",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @GetMapping("/users/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("email");
        session.removeAttribute("userName");
        session.removeAttribute("userId");
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 會員密碼修改，暫時從缺
    // 會員交易平台點數儲值、扣款
    @Operation(summary = "會員點數交易功能", description = "儲值或交易扣款都用這個",
            // 設定 Swagger requestBody
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserLoginRequest.class))),
            // 設定 Swagger responseBody
            responses = {
                    @ApiResponse(responseCode = "200", description = "point 更新成功",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = UserEntity.class))),
                    @ApiResponse(responseCode = "400", description = " point 參數錯誤，可能由以下原因產生：\n"
                            + "- 帳戶餘額不足\n",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class))),
                    @ApiResponse(responseCode = "401", description = "尚未登入",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseBody.class)))}
    )
    @PutMapping("/users/updatePoint")
    public ResponseEntity<?> updatePoint(@RequestParam Integer point,
                                         HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        UserEntity user = userService.updatePoint(userId, point);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
