package com.tong.mallbackend.exceptionhandler;

import com.tong.mallbackend.dto.ErrorResponseBody;
import com.tong.mallbackend.exceptions.MyUserException;
import com.tong.mallbackend.exceptions.ProductCategoryException;
import com.tong.mallbackend.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

/**
 * ClassName: MyExceptionHandler
 * Package: com.tong.mallbackend.exceptionhandler
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> handle(MethodArgumentNotValidException exception) {
        log.warn("前端參數錯誤訊息:{}", Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseBody(exception.getClass().getName(), exception.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(MyUserException.class)
    public ResponseEntity<ErrorResponseBody> handle(MyUserException exception) {
        log.warn(" User 錯誤訊息:{}", exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(new ErrorResponseBody(exception));
    }

    @ExceptionHandler(ProductCategoryException.class)
    public ResponseEntity<ErrorResponseBody> handle(ProductCategoryException exception) {
        log.warn(" ProductCategory 錯誤訊息:{}", exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(new ErrorResponseBody(exception));
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponseBody> handle(ProductException exception) {
        log.warn(" ProductException 錯誤訊息:{}", exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(new ErrorResponseBody(exception));
    }

}
