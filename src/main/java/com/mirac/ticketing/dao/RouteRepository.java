package com.mirac.ticketing.dao;

import com.mirac.ticketing.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author miracy
 */
public interface RouteRepository extends JpaRepository<Route, Long> {

    Route findByRouteNumber(Integer number);

    /*
    Prepares like search query for both of the parameters
     */
    List<Route> findByFromAirportIgnoreCaseContainingOrToAirportIgnoreCaseContaining(String fromAirport, String toAirport);
}
