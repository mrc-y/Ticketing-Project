package com.mirac.ticketing.validator;

import com.mirac.ticketing.model.entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author miracy
 */
@Component
public class TicketStatusUpdateValidator {

    public void validate(Ticket ticket, String status) {
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket does not exists");
        } else if (ticket.getStatus().equalsIgnoreCase(status)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Status is already %s", status));
        }
    }
}
