package com.mirac.ticketing.api;

import com.mirac.ticketing.model.dto.RouteDTO;
import com.mirac.ticketing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling Route operations
 *
 * @author miracy
 */
@RestController
@RequestMapping(value = "/api")
public class RouteApi {
    @Autowired
    RouteService routeService;

    @PostMapping(value = "/route", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<RouteDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        RouteDTO createdRouteDTO = routeService.createRoute(routeDTO);
        return new ResponseEntity<>(createdRouteDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/route/{number}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<RouteDTO> getRouteByNumber(@PathVariable("number") Integer routeNumber) {
        RouteDTO routeDTO = routeService.getRoute(routeNumber);
        return new ResponseEntity<>(routeDTO, routeDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/route", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List> searchRoutesByAirportName(@RequestParam("airportName") String airportName) {
        List<RouteDTO> routeDTOList = routeService.searchRoutes(airportName);
        return new ResponseEntity<>(routeDTOList, routeDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
