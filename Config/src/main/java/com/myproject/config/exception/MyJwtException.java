package com.myproject.config.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class MyJwtException extends AuthenticationException {
    private HttpStatus httpStatus;

    public MyJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyJwtException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
