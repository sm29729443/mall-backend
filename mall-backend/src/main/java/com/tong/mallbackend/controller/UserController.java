package com.tong.mallbackend.controller;

import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.model.UserEntity;
import com.tong.mallbackend.service.UserService;
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
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 會員註冊
    @PostMapping(value = "/public/users/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getRepeatPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("兩次密碼不一");
        }
        UserEntity userEntity = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    // 會員登入
    // 會員登出
    // 會員刪除
    // 會員密碼修改
    // 會員交易平台點數儲值
    // 會員交易平台點數扣款

}
