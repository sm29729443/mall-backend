package com.tong.mallbackend.exceptions;

import org.springframework.http.HttpStatus;

/**
 * ClassName: CartException
 * Package: com.tong.mallbackend.exceptions
 */
public class CartException extends RuntimeException {
    private HttpStatus status;

    public CartException(HttpStatus status) {
        this.status = status;
    }

    public CartException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CartException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public CartException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public CartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
