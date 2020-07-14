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
public class RouteDTO implements TransferObject{
    @NonNull
    private String fromAirport;
    @NonNull
    private String toAirport;
    @NonNull
    private Integer routeNumber;
}
