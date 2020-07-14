package com.mirac.ticketing.validator;

import com.mirac.ticketing.dao.AirportRepository;
import com.mirac.ticketing.model.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class AirportCreateValidator implements Validator<Airport> {
    @Autowired
    AirportRepository airportRepository;

    @Override
    public void validate(Airport airport) {
        Airport existingAirport = airportRepository.findByAirportCodeIgnoreCase(airport.getAirportCode());
        if (existingAirport != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airport already exists");
        }
    }

}
