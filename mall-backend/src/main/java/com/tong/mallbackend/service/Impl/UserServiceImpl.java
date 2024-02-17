package com.tong.mallbackend.service.Impl;

import com.tong.mallbackend.dao.UserDao;
import com.tong.mallbackend.dto.UserLoginRequest;
import com.tong.mallbackend.dto.UserRegisterRequest;
import com.tong.mallbackend.exceptions.MyUserException;
import com.tong.mallbackend.models.UserEntity;
import com.tong.mallbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

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
            throw new MyUserException("email 已被註冊", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUserName(request.getName());

        String encodePassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodePassword);
        user.setEmail(request.getEmail());
        user.setPoint(0);

        // 時間設置
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        user.setRegistrationDate(now);

        UserEntity userByCreate = userDao.save(user);
        return userByCreate;
    }

    @Override
    public UserEntity login(UserLoginRequest request, HttpSession session) {
        UserEntity user = userDao.getByEmail(request.getEmail());
        if (user == null) {
            log.warn("此 email 尚未註冊:{}", request.getEmail());
            throw new MyUserException("信箱或密碼錯誤", HttpStatus.NOT_FOUND);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("此 email 密碼輸入錯誤:{}", request.getEmail());
            throw new MyUserException("信箱或密碼錯誤", HttpStatus.NOT_FOUND);
        }
        session.setAttribute("email", user.getEmail());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userId", user.getUserId());
        return user;
    }

    @Transactional
    @Override
    public UserEntity updatePoint(Integer userId, Integer point) {
        UserEntity user = userDao.findById(userId).get();
        if (point < 0 && Math.abs(point) > user.getPoint()) {
            throw new MyUserException("扣款失敗，帳戶餘額不足", HttpStatus.BAD_REQUEST);
        }
        Integer newPoint = user.getPoint() + point;
        user.setPoint(newPoint);
        return user;
    }
}
