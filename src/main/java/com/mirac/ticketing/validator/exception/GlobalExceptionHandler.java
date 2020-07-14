package com.mirac.ticketing.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handlyMyCustomException(ResponseStatusException e) {
        return new ResponseEntity<>("Error happened: " + e.getReason(), HttpStatus.BAD_REQUEST);
    }
}