package com.tong.mallbackend.interceptor;

import com.tong.mallbackend.exceptions.MyUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName: UserInterceptor
 * Package: com.tong.mallbackend.interceptor
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") == null) {
            log.warn("尚未登入");
            throw new MyUserException("尚未登入", HttpStatus.UNAUTHORIZED);
        }
        return true;

    }
}
