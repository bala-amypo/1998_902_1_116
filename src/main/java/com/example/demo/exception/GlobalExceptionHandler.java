package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiException(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiException> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                new ApiException(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGeneral(Exception ex) {
        return new ResponseEntity<>(
                new ApiException(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
