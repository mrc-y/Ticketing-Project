package com.mirac.ticketing.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author miracy
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Flight Not Found")
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message){
        super(message);
    }
}
