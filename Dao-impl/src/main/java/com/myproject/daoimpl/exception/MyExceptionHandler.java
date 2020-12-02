package com.myproject.daoimpl.exception;

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

    @Data
    private static class AwesomeException {
        private String message;

        public AwesomeException(String s) {
            message = s;
        }
    }
}
