package com.myproject.daoimpl.exception;

import com.myproject.config.exception.MyJwtException;
import com.myproject.serviceimpl.exceptions.RentalPointServiceException;
import com.myproject.serviceimpl.exceptions.ScooterServiceException;
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j
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

    @ExceptionHandler(MyJwtException.class)
    protected ResponseEntity<AwesomeException> tokenIsNotAvailableUserException() {
        return new ResponseEntity<>(new AwesomeException("JWT validity cannot be asserted and should not be trusted"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ScooterServiceException.class)
    protected ResponseEntity<AwesomeException> tokenIsNotAvailableScooterException() {
        return new ResponseEntity<>(new AwesomeException("The requested resource is not available"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalPointServiceException.class)
    protected ResponseEntity<AwesomeException> tokenIsNotAvailableRentalPointsException() {
        return new ResponseEntity<>(new AwesomeException("The requested resource is not available"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<AwesomeException> UserNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("User not found, check login and password"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceValidationException.class)
    protected ResponseEntity<DetailsAwesomeException> validationException() {
        return new ResponseEntity<>(new DetailsAwesomeException("Please, input valid data", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }


    @Data
    private static class AwesomeException {
        private String message;

        public AwesomeException(String s) {
            message = s;
            log.error(s);
        }
    }

    @Data
    private static class DetailsAwesomeException {
        private String message;
        private int code;

        public DetailsAwesomeException(String s, int status) {
            message = s;
            code = status;
            log.error(s + status);
        }
    }
}
