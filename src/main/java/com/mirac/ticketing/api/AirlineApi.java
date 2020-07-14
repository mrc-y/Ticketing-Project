package com.mirac.ticketing.api;

import com.mirac.ticketing.model.dto.AirlineDTO;
import com.mirac.ticketing.service.AirlineService;
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
 * Controller class for handling Airline operations
 *
 * @author miracy
 */
@RestController
@RequestMapping(value = "/api")
public class AirlineApi {
    @Autowired
    AirlineService airlineService;

    @PostMapping(value = "/airline", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<AirlineDTO> createAirline(@Valid @RequestBody AirlineDTO airlineDTO) {
        AirlineDTO createdAirlineDTO = airlineService.createAirline(airlineDTO);
        return new ResponseEntity<>(createdAirlineDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/airline/{code}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<AirlineDTO> getAirlineByCode(@PathVariable("code") String code) {
        AirlineDTO airlineDTO = airlineService.getAirline(code);
        return new ResponseEntity<>(airlineDTO, airlineDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/airline", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List> searchAirlinesByName(@RequestParam("name") String name) {
        List<AirlineDTO> airlineDTOList = airlineService.searchAirlines(name);
        return new ResponseEntity<>(airlineDTOList, airlineDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
