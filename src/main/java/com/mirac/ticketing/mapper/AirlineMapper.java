package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.AirlineDTO;
import com.mirac.ticketing.model.entity.Airline;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author miracy
 */
@Component
@Mapper(componentModel = "spring")
public abstract class AirlineMapper extends AbstractMapper<AirlineDTO, Airline> {
}
