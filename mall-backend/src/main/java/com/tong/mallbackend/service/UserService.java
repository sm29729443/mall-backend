package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.UserLoginRequest;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.models.UserEntity;

import javax.servlet.http.HttpSession;

/**
 * ClassName: UserService
 * Package: com.tong.mallbackend.service
 */
public interface UserService {
    public UserEntity register(UserRegisterRequest request);

    UserEntity login(UserLoginRequest request, HttpSession session);

    UserEntity updatePoint(Integer userId, Integer point);
}
