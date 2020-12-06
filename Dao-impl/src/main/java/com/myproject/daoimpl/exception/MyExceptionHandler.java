package com.myproject.daoimpl.exception;

import com.myproject.serviceimpl.exceptions.UserServiceException;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestedResourceIsNotAvailableException.class)
    protected ResponseEntity<AwesomeException> requestedResourceIsNotAvailableException() {
        return new ResponseEntity<>(new AwesomeException("The requested resource is not available"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserServiceException.class)
    protected ResponseEntity<AwesomeException> requestedResourceIsNotAvailableUserException() {
        return new ResponseEntity<>(new AwesomeException("The requested resource is not available"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<AwesomeException> tokenIsNotAvailableUserException() {
        return new ResponseEntity<>(new AwesomeException("WT validity cannot be asserted and should not be trusted"), HttpStatus.FORBIDDEN);
    }


    @Data
    private static class AwesomeException {
        private String message;

        public AwesomeException(String s) {
            message = s;
        }
    }
}
