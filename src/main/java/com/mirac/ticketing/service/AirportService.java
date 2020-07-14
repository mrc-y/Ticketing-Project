package com.mirac.ticketing.service;

import com.mirac.ticketing.dao.AirportRepository;
import com.mirac.ticketing.mapper.AirportMapper;
import com.mirac.ticketing.model.dto.AirportDTO;
import com.mirac.ticketing.model.entity.Airport;
import com.mirac.ticketing.validator.AirportCreateValidator;
import com.mirac.ticketing.validator.AirportSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author miracy
 */
@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    AirportMapper airportMapper;
    @Autowired
    AirportSearchValidator airportSearchValidator;
    @Autowired
    AirportCreateValidator airportCreateValidator;

    @Transactional
    public AirportDTO createAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.convert(airportDTO);
        airportCreateValidator.validate(airport);
        Airport savedAirport = airportRepository.save(airport);
        return airportMapper.convert(savedAirport);
    }

    @Transactional
    public AirportDTO getAirport(String airportCode) {
        Airport airport = airportRepository.findByAirportCodeIgnoreCase(airportCode);
        return airportMapper.convert(airport);
    }

    @Transactional
    public List<AirportDTO> searchAirports(String name) {
        airportSearchValidator.validate(name);
        List<Airport> airportList = airportRepository.findByAirportNameIgnoreCaseContaining(name);
        return airportMapper.mapToDTOList(airportList);
    }
}
