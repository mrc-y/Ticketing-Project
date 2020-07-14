package com.mirac.ticketing.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author miracy
 */
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Airline implements EntityObject {
    @GeneratedValue
    @Id
    private Long id;
    private String airlineName;
    @Column(unique = true)
    private String airlineCode;
}
