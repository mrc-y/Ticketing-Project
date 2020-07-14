package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.AirportDTO;
import com.mirac.ticketing.model.entity.Airport;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author miracy
 */
@Component
@Mapper(componentModel = "spring")
public abstract class AirportMapper extends AbstractMapper<AirportDTO, Airport>{
}
