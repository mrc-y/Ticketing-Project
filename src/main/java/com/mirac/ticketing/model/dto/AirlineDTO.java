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
public class AirlineDTO implements TransferObject {
    @NonNull
    private String airlineName;
    @NonNull
    private String airlineCode;
}
