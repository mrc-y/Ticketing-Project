package com.mirac.ticketing.dao;

import com.mirac.ticketing.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author miracy
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findByAirportCodeIgnoreCase(String code);

    /**
     * Searches airports by name with like search
     *
     * @param name
     * @return list of airports
     */
    List<Airport> findByAirportNameIgnoreCaseContaining(String name);
}
