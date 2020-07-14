package com.mirac.ticketing.validator;

import com.mirac.ticketing.dao.AirlineRepository;
import com.mirac.ticketing.dao.RouteRepository;
import com.mirac.ticketing.model.entity.Airline;
import com.mirac.ticketing.model.entity.Flight;
import com.mirac.ticketing.model.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class FlightCreateValidator implements Validator<Flight> {
    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    RouteRepository routeRepository;

    @Override
    public void validate(Flight flight) {
        Airline airline = airlineRepository.findByAirlineCodeIgnoreCase(flight.getAirlineCode());
        if (airline == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airline does not exists");
        }
        Route route = routeRepository.findByRouteNumber(flight.getRouteNumber());
        if (route == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Route does not exists");
        }
    }

}
