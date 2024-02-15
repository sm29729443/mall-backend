package com.tong.mallbackend.service;

import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.model.UserEntity;

/**
 * ClassName: UserService
 * Package: com.tong.mallbackend.service
 */
public interface UserService {
    public UserEntity register(UserRegisterRequest request);
}
