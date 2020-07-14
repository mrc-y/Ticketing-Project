package com.mirac.ticketing.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class AirlineSearchValidator implements Validator<String> {

    @Override
    public void validate(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam name should not be empty");
        } else if (name.length() <= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam name length should be more than 2");
        }
    }
}
