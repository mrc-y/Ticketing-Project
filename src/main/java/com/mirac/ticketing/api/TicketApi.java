package com.mirac.ticketing.api;

import com.mirac.ticketing.model.dto.TicketDTO;
import com.mirac.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
 * Controller class for handling Ticket operations
 *
 * @author miracy
 */
@RestController
@RequestMapping(value = "/api")
public class TicketApi {

    @Autowired
    TicketService ticketService;

    @PostMapping(value = "ticket/purchase", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<TicketDTO> purchaseTicket (@Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicketDTO = ticketService.purchaseTicket(ticketDTO);
        return new ResponseEntity<>(createdTicketDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/ticket/{number}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<TicketDTO> getTicketById(@PathVariable("number") String ticketNumber) {
        TicketDTO ticketDTO = ticketService.getTicket(ticketNumber);
        return new ResponseEntity<>(ticketDTO, ticketDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/ticket", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List> searchTickets(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                       @RequestParam(required = false) Long flightId) {
        List<TicketDTO> ticketDTOList = ticketService.searchTickets(name, surname, flightId);
        return new ResponseEntity<>(ticketDTOList, ticketDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PatchMapping(value = "/ticket/{number}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<TicketDTO> updateTicketStatus(@PathVariable("number") String ticketNumber, @Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO patchedTicketDTO = ticketService.updateStatus(ticketNumber, ticketDTO.getStatus().name());
        return new ResponseEntity<>(patchedTicketDTO, patchedTicketDTO == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
