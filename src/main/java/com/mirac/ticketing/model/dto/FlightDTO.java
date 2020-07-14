package com.mirac.ticketing.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author miracy
 */
@Getter @Setter @NoArgsConstructor @ToString
public class FlightDTO implements TransferObject {
    private Long id;
    @NonNull
    private String airlineCode;
    @NonNull
    private Integer routeNumber;
    private String flightCode;
    @NonNull
    private LocalDateTime flightDate;
    @NonNull
    private Integer planeCapacity;
    private Integer soldTickets;
    @NonNull
    private Integer minAvailableTicketPrice;
}
