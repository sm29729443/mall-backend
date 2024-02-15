package com.tong.mallbackend.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * ClassName: MyExceptionHandler
 * Package: com.tong.mallbackend.exceptionhandler
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
        log.warn("前端參數錯誤訊息:{}", Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body(exception.getFieldError().getDefaultMessage());
    }
}
