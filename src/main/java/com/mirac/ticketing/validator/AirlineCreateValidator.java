package com.mirac.ticketing.validator;

import com.mirac.ticketing.dao.AirlineRepository;
import com.mirac.ticketing.model.entity.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class AirlineCreateValidator implements Validator<Airline> {
    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public void validate(Airline airline) {
        Airline existingAirline = airlineRepository.findByAirlineCodeIgnoreCase(airline.getAirlineCode());
        if (existingAirline != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airline already exists");
        }
    }
}
