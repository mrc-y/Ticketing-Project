package com.mirac.ticketing.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author miracy
 */
@Getter @Setter @NoArgsConstructor @ToString
public class TicketDTO implements TransferObject {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private Long flightId;
    private String ticketNumber;
    @NonNull
    private Integer ticketPrice;
    @NonNull
    private String creditCardNumber;
    private StatusEnum status = StatusEnum.ACTIVE;

    public enum StatusEnum {
        ACTIVE,
        CANCELLED;

        public static StatusEnum fromValue(String value) {
            return value == null ? null : StatusEnum.valueOf(value.toUpperCase());
        }
    }
}
