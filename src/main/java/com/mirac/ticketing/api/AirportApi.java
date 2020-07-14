package com.mirac.ticketing.api;

import com.mirac.ticketing.model.dto.AirportDTO;
import com.mirac.ticketing.service.AirportService;
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
 *  Controller class for handling Airport operations
 *
 * @author miracy
 */
@RestController
@RequestMapping(value = "/api")
public class AirportApi {
    @Autowired
    AirportService airportService;

    @PostMapping(value = "/airport", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<AirportDTO> createAirport(@Valid @RequestBody AirportDTO airportDTO) {
        AirportDTO createdAirportDTO = airportService.createAirport(airportDTO);
        return new ResponseEntity<>(createdAirportDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/airport/{code}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<AirportDTO> getAirportByCode(@PathVariable("code") String code) {
        AirportDTO airportDTO = airportService.getAirport(code);
        return new ResponseEntity<>(airportDTO, airportDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/airport", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List> searchAirportsByName(@RequestParam("name") String name) {
        List<AirportDTO> airportDTOList = airportService.searchAirports(name);
        return new ResponseEntity<>(airportDTOList, airportDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
