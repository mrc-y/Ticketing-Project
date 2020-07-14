package com.mirac.ticketing.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author miracy
 */
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Flight implements EntityObject {
    @GeneratedValue
    @Id
    private Long id;
    private String airlineCode;
    private Integer routeNumber;
    private String flightCode;
    private LocalDateTime flightDate;
    private Integer planeCapacity;
    private Integer soldTickets;
    private Integer minAvailableTicketPrice;

    /**
     * Updates the ticket number when a new ticket is sold or cancelled
     *
     * @param value
     */
    public void incrementSoldTicketNumber(int value) {
        this.soldTickets += value;
    }

    /**
     * Updates min ticket price for the flight
     *
     * @param value
     */
    public void multiplyMinTicketPriceByValue(double value) {
        this.minAvailableTicketPrice = (int) (Math.round(this.minAvailableTicketPrice * value));
    }
}