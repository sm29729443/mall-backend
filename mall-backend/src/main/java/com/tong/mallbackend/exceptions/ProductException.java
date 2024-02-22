package com.tong.mallbackend.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * ClassName: ProductException
 * Package: com.tong.mallbackend.exceptions
 */
@Setter
@Getter
public class ProductException extends RuntimeException {
    private HttpStatus status;

    public ProductException(HttpStatus status) {
        this.status = status;
    }

    public ProductException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ProductException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public ProductException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public ProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
