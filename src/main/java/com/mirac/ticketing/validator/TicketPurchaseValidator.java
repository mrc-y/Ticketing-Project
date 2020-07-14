package com.mirac.ticketing.validator;

import com.mirac.ticketing.dao.FlightRepository;
import com.mirac.ticketing.dao.TicketRepository;
import com.mirac.ticketing.model.dto.TicketDTO;
import com.mirac.ticketing.model.entity.Flight;
import com.mirac.ticketing.model.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * @author miracy
 */
@Component
public class TicketPurchaseValidator implements Validator<Ticket> {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void validate(Ticket ticket) {
        Ticket existingTicket = ticketRepository.findByTicketNumber(ticket.getTicketNumber());
        if (existingTicket != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket already exists");
        }
        if (ticket.getCreditCardNumber().length() != 16){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit card number should be 16 digits");
        }
        Optional<Flight> flight = flightRepository.findById(ticket.getFlightId());
        if (!flight.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight does not exists");
        }
        if (ticket.getTicketPrice() < flight.get().getMinAvailableTicketPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket price can't be lower than min available price");
        }
        if(flight.get().getSoldTickets() == flight.get().getPlaneCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plane is already full");
        }
        if(TicketDTO.StatusEnum.CANCELLED.name().equalsIgnoreCase(ticket.getStatus())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't purchase a cancelled ticket");
        }
    }
}
