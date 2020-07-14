package com.mirac.ticketing.service;

import com.mirac.ticketing.dao.FlightRepository;
import com.mirac.ticketing.mapper.FlightMapper;
import com.mirac.ticketing.model.dto.FlightDTO;
import com.mirac.ticketing.model.entity.Flight;
import com.mirac.ticketing.validator.FlightCreateValidator;
import com.mirac.ticketing.validator.FlightSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * @author miracy
 */
@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    FlightMapper flightMapper;
    @Autowired
    FlightSearchValidator flightSearchValidator;
    @Autowired
    FlightCreateValidator flightCreateValidator;

    @Transactional
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.convert(flightDTO);
        flightCreateValidator.validate(flight);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.convert(savedFlight);
    }

    @Transactional
    public FlightDTO getFlight(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (!flight.isPresent()) {
            return null;
        }
        return flightMapper.convert(flight.get());
    }

    @Transactional
    public List<FlightDTO> searchFlights(String airlineCode, Integer routeNumber, LocalDate flightDate) {
        flightSearchValidator.validate(airlineCode, routeNumber, flightDate);
        List<Flight> flightList = searchFlightsFromDB(airlineCode, routeNumber, flightDate);
        return flightMapper.mapToDTOList(flightList);
    }

    /**
     * Prepares the parameters for the search operation
     *
     * @param airlineCode
     * @param routeNumber
     * @param flightDate
     * @return list of flights from the DB
     */
    private List<Flight> searchFlightsFromDB(String airlineCode, Integer routeNumber, LocalDate flightDate) {
        // the reason to create these variables is that we want to search the DB for a specific day.
        // and the best way to do this is checking dateTime column between 00:00 and 23:59
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (flightDate != null) {
            startDate = flightDate.atStartOfDay();
            endDate = flightDate.atTime(LocalTime.MAX);
        }
        return flightRepository.findByRouteNumberAndAirlineCodeAndFlightDateBetween(routeNumber, airlineCode, startDate, endDate);
    }
}
