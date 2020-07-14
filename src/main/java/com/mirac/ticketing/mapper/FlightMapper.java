package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.FlightDTO;
import com.mirac.ticketing.model.entity.Flight;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * @author miracy
 */
@Component
@Mapper(componentModel = "spring")
public abstract class FlightMapper extends AbstractMapper<FlightDTO, Flight> {

    @Mapping(target = "flightCode", expression = "java(flightDTO.getAirlineCode() + Integer.toString(flightDTO.getRouteNumber()))")
    public abstract Flight convert (FlightDTO flightDTO);

    @AfterMapping
    public void mapSoldTickets(@MappingTarget Flight flight){
        // sold ticket value should not be persisted as null
        if (flight.getSoldTickets() == null) {
            flight.setSoldTickets(0);
        }
    }

}
