package com.devracoon.redis.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> hadleException(HttpRequestMethodNotSupportedException e) {
        log.error("hadleException", e);
        ErrorResponse response
                = ErrorResponse
                .builder()
                .errorCode("1000")
                .errorMsg(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
