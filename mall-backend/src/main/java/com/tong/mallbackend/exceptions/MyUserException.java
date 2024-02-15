package com.tong.mallbackend.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * ClassName: UserException
 * Package: com.tong.mallbackend.exceptions
 */
@Setter
@Getter
public class MyUserException extends RuntimeException{
    private HttpStatus status;
    public MyUserException() {
    }

    public MyUserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public MyUserException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public MyUserException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public MyUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
