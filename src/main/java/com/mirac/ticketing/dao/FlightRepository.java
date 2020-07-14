package com.mirac.ticketing.dao;

import com.mirac.ticketing.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author miracy
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*
    Custom query was needed because if a parameter is null, it needs to be ignored
     */
    @Query("SELECT f FROM Flight f WHERE (:number is null or f.routeNumber = :number) and (:code is null"
            + " or f.airlineCode = :code) and (:startDate is null or f.flightDate between :startDate and :endDate)")
    List<Flight> findByRouteNumberAndAirlineCodeAndFlightDateBetween(@Param("number") Integer number, @Param("code") String code,
                                                                     @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
