package com.mirac.ticketing.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class TicketSearchValidator {

    public void validate(String name, String surname, Long flightId){
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(surname) && flightId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParams should not be empty");
        }
    }
}
