package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.dao.UserDao;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.exceptions.MyUserException;
import com.tong.mallbackend.model.UserEntity;
import com.tong.mallbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: UserServiceImpl
 * Package: com.tong.mallbackend.service.Impl
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(UserRegisterRequest request) {
        UserEntity userEntity = userDao.getByEmail(request.getEmail());
        if (userEntity != null) {
            log.warn("該 email {} 已被註冊", request.getEmail());
            throw new MyUserException("信箱已被註冊", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUserName(request.getName());

        String encodePassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodePassword);
        user.setEmail(request.getEmail());

        // 時間設置
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        user.setRegistrationDate(now);

        UserEntity userByCreate = userDao.save(user);
        return userByCreate;
    }
}
