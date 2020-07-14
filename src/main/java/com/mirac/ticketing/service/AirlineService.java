package com.mirac.ticketing.service;

import com.mirac.ticketing.dao.AirlineRepository;
import com.mirac.ticketing.mapper.AirlineMapper;
import com.mirac.ticketing.model.dto.AirlineDTO;
import com.mirac.ticketing.model.entity.Airline;
import com.mirac.ticketing.validator.AirlineCreateValidator;
import com.mirac.ticketing.validator.AirlineSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author miracy
 */
@Service
public class AirlineService {
    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    AirlineMapper airlineMapper;
    @Autowired
    AirlineSearchValidator  airlineSearchValidator;
    @Autowired
    AirlineCreateValidator airlineCreateValidator;

    @Transactional
    public AirlineDTO createAirline(AirlineDTO airlineDTO) {
        Airline airline = airlineMapper.convert(airlineDTO);
        airlineCreateValidator.validate(airline);
        Airline savedAirline = airlineRepository.save(airline);
        return airlineMapper.convert(savedAirline);
    }

    @Transactional
    public AirlineDTO getAirline(String airlineCode) {
        Airline airline = airlineRepository.findByAirlineCodeIgnoreCase(airlineCode);
        return airlineMapper.convert(airline);
    }

    @Transactional
    public List<AirlineDTO> searchAirlines(String name) {
        airlineSearchValidator.validate(name);
        List<Airline> airlineList = airlineRepository.findByAirlineNameIgnoreCaseContaining(name);
        return airlineMapper.mapToDTOList(airlineList);
    }
}
