package com.mirac.ticketing.service;

import com.mirac.ticketing.dao.FlightRepository;
import com.mirac.ticketing.dao.TicketRepository;
import com.mirac.ticketing.mapper.TicketMapper;
import com.mirac.ticketing.model.dto.TicketDTO;
import com.mirac.ticketing.model.entity.Flight;
import com.mirac.ticketing.model.entity.Ticket;
import com.mirac.ticketing.validator.TicketPurchaseValidator;
import com.mirac.ticketing.validator.TicketSearchValidator;
import com.mirac.ticketing.validator.TicketStatusUpdateValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author miracy
 */
@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    TicketPurchaseValidator ticketPurchaseValidator;
    @Autowired
    TicketSearchValidator ticketSearchValidator;
    @Autowired
    TicketStatusUpdateValidator ticketStatusUpdateValidator;

    @Transactional
    public TicketDTO purchaseTicket(TicketDTO ticketDTO) {
        ticketDTO.setTicketNumber(createTicketNumber(ticketDTO));
        removeNonDigitsFromCreditCardNo(ticketDTO);
        Ticket ticket = ticketMapper.convert(ticketDTO);
        ticketPurchaseValidator.validate(ticket);
        handleFlightInfo(ticket, 1.1d, 1, 1);
        maskCreditCardNo(ticket, 6, 12, '*');
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.convert(savedTicket);
    }

    @Transactional
    public TicketDTO getTicket(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        return ticketMapper.convert(ticket);
    }

    @Transactional
    public List<TicketDTO> searchTickets(String name, String surname, Long flightId) {
        ticketSearchValidator.validate(name, surname, flightId);
        List<Ticket> ticketList = ticketRepository.findByNameAndSurnameAnAndFlightId(name, surname, flightId);
        return ticketMapper.mapToDTOList(ticketList);
    }

    @Transactional
    public TicketDTO updateStatus(String ticketNumber, String status) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        ticketStatusUpdateValidator.validate(ticket, status);

        if (TicketDTO.StatusEnum.CANCELLED.name().equalsIgnoreCase(status)) {
            // if a ticket is cancelled, we need to re-calculate the minTicketPrice and decrease soldTickets number
            // here 10d/11 is used to revert the price, because we are increasing the ticket price 10%
            handleFlightInfo(ticket, 10d / 11, -1, 0);
        } else {
            // 10% increase for the min ticket price if ticket number is above threshold
            handleFlightInfo(ticket, 1.1d, 1, 1);
        }

        ticket.setStatus(status);
        // no need to save the ticket since the entity will be saved when the transaction is closed
        return ticketMapper.convert(ticket);
    }

    /**
     * Updates flight entity after the ticket purchase or cancellation
     *
     * @param ticket
     * @param multiplyValue
     * @param incrementValue
     * @param calculationValue
     */
    private void handleFlightInfo(Ticket ticket, double multiplyValue, int incrementValue, int calculationValue) {
        Flight flight = getFlightFromFlightId(ticket.getFlightId());
        calculateMinTicketPrice(flight, flight.getSoldTickets() - 1 + calculationValue, flight.getSoldTickets() + calculationValue, multiplyValue);
        flight.incrementSoldTicketNumber(incrementValue);
    }

    private Flight getFlightFromFlightId(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if (!flight.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight does not exists");
        }
        return flight.get();
    }

    /**
     * This method detects if a ticket purchase(or cancellation) makes soldTickets/planeCapacity exceed 10% thresholds
     *
     * @param flight
     * @param oldTicketCount
     * @param newTicketCount
     * @param d
     */
    private void calculateMinTicketPrice(Flight flight, int oldTicketCount, int newTicketCount, double d) {
        // since we are multiplying by 10 for each 10% increase, the difference should be 1 (since integer is rounding down)
        if (newTicketCount * 10 / flight.getPlaneCapacity() == (oldTicketCount * 10 / flight.getPlaneCapacity()) + 1) {
            flight.multiplyMinTicketPriceByValue(d);
        }
    }

    /**
     * Creates unique ticket number if it's not given
     *
     * @param ticketDTO
     * @return ticket number
     */
    private String createTicketNumber(TicketDTO ticketDTO) {
        if (StringUtils.isEmpty(ticketDTO.getTicketNumber())) {
            return new ObjectId().toString();
        }
        return ticketDTO.getTicketNumber();
    }

    /**
     * Removes unnecessary characters like '-' '#' '_' from the credit card number.
     *
     * @param ticketDTO
     */
    private void removeNonDigitsFromCreditCardNo(TicketDTO ticketDTO) {
        StringBuilder builder = new StringBuilder(ticketDTO.getCreditCardNumber().length());
        for (char c : ticketDTO.getCreditCardNumber().toCharArray()) {
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        ticketDTO.setCreditCardNumber(builder.toString());
    }

    /**
     * Masks some part of the credit card for security purposes
     *
     * @param ticket
     * @param startIndex
     * @param endIndex
     * @param maskChar
     */
    private void maskCreditCardNo(Ticket ticket, int startIndex, int endIndex, char maskChar) {
        String cardNo = ticket.getCreditCardNumber();
        int maskLength = endIndex - startIndex;
        StringBuilder stringBuilder = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
            stringBuilder.append(maskChar);
        }

        String maskString = stringBuilder.toString();

        String maskedCardNo = cardNo.substring(0, startIndex) + maskString + cardNo.substring(endIndex);

        ticket.setCreditCardNumber(maskedCardNo);
    }

}
