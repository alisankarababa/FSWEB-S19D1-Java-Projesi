package com.workintech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomExceptionResponse> hCustomException(CustomException customException) {
        CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(customException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(customExceptionResponse, customException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<CustomExceptionResponse> hException(Exception exception) {
        CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(customExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
