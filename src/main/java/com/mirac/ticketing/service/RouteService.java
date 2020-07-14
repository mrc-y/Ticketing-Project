package com.mirac.ticketing.service;

import com.mirac.ticketing.dao.RouteRepository;
import com.mirac.ticketing.mapper.RouteMapper;
import com.mirac.ticketing.model.dto.RouteDTO;
import com.mirac.ticketing.model.entity.Route;
import com.mirac.ticketing.validator.RouteCreateValidator;
import com.mirac.ticketing.validator.RouteSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author miracy
 */
@Service
public class RouteService {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    RouteSearchValidator routeSearchValidator;
    @Autowired
    RouteCreateValidator routeCreateValidator;

    @Transactional
    public RouteDTO createRoute(RouteDTO routeDTO) {
        Route route = routeMapper.convert(routeDTO);
        routeCreateValidator.validate(route);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.convert(savedRoute);
    }

    @Transactional
    public RouteDTO getRoute(Integer routeNumber) {
        Route route = routeRepository.findByRouteNumber(routeNumber);
        return routeMapper.convert(route);
    }

    @Transactional
    public List<RouteDTO> searchRoutes(String airportName) {
        routeSearchValidator.validate(airportName);
        List<Route> routeList = routeRepository.findByFromAirportIgnoreCaseContainingOrToAirportIgnoreCaseContaining(airportName, airportName);
        return routeMapper.mapToDTOList(routeList);
    }
}
