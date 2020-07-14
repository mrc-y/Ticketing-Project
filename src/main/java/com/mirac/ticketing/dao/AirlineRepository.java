package com.mirac.ticketing.dao;

import com.mirac.ticketing.model.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author miracy
 */
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Airline findByAirlineCodeIgnoreCase(String code);

    /**
     * Searches airlines by name with like search
     *
     * @param name
     * @return list of airlines
     */
    List<Airline> findByAirlineNameIgnoreCaseContaining(String name);
}
