package com.mirac.ticketing.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class AirportSearchValidator implements Validator<String> {

    @Override
    public void validate(String airportName) {
        if (StringUtils.isEmpty(airportName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam airportName should not be empty");
        } else if (airportName.length() <= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam airportName length should be more than 2");
        }
    }
}
