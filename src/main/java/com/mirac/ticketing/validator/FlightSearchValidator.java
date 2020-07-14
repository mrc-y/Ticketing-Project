package com.mirac.ticketing.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

/**
 * @author miracy
 */
@Component
public class FlightSearchValidator {

    public void validate(String airlineCode, Integer routeNumber, LocalDate flightDate) {
        if (StringUtils.isEmpty(airlineCode) && routeNumber == null && flightDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParams should not be empty");
        }
    }
}
