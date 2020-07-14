package com.mirac.ticketing.api;

import com.mirac.ticketing.model.dto.FlightDTO;
import com.mirac.ticketing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDate;
import java.util.List;

/**
 * Controller class for handling Flight operations
 *
 * @author miracy
 */
@RestController
@RequestMapping(value = "/api")
public class FlightApi {
    @Autowired
    FlightService flightService;

    @PostMapping(value = "/flight", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<FlightDTO> createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        FlightDTO createdFlightDTO = flightService.createFlight(flightDTO);
        return new ResponseEntity<>(createdFlightDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/flight/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<FlightDTO> getFlightById(@PathVariable("id") Long id) {
        FlightDTO flightDTO = flightService.getFlight(id);
        return new ResponseEntity<>(flightDTO, flightDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/flight", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List> searchFlights(@RequestParam(required = false) String airlineCode, @RequestParam(required = false) Integer routeNumber,
                                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate) {
        List<FlightDTO> flightDTOList = flightService.searchFlights(airlineCode, routeNumber, flightDate);
        return new ResponseEntity<>(flightDTOList, flightDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
