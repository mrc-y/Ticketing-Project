package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.RouteDTO;
import com.mirac.ticketing.model.entity.Route;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author miracy
 */
@Component
@Mapper(componentModel = "spring")
public abstract class RouteMapper extends AbstractMapper<RouteDTO, Route> {
}
