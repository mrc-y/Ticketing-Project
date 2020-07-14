package com.mirac.ticketing.validator;

import com.mirac.ticketing.dao.AirportRepository;
import com.mirac.ticketing.dao.RouteRepository;
import com.mirac.ticketing.model.entity.Airport;
import com.mirac.ticketing.model.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class RouteCreateValidator implements Validator<Route> {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    AirportRepository airportRepository;

    @Override
    public void validate(Route route) {
        Route existingRoute = routeRepository.findByRouteNumber(route.getRouteNumber());
        if (existingRoute != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Route already exists");
        }
        Airport fromAirport = airportRepository.findByAirportCodeIgnoreCase(route.getFromAirport());
        Airport toAirport = airportRepository.findByAirportCodeIgnoreCase(route.getToAirport());
        if(toAirport == null || fromAirport == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airport does not exists");
        }
    }

}
