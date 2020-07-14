package com.mirac.ticketing.mapper;

import com.mirac.ticketing.model.dto.TicketDTO;
import com.mirac.ticketing.model.entity.Ticket;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author miracy
 */
@Component
@Mapper(componentModel = "spring")
public abstract class TicketMapper extends AbstractMapper<TicketDTO, Ticket> {
}
