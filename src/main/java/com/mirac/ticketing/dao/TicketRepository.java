package com.mirac.ticketing.dao;

import com.mirac.ticketing.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author miracy
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByTicketNumber(String ticketNumber);

    /*
    Custom query was needed because if a parameter is null, it needs to be ignored
    */
    @Query("SELECT t FROM Ticket t WHERE (:name is null or t.name = :name) and (:surname is null"
            + " or t.surname = :surname) and (:flightId is null or t.flightId = :flightId)")
    List<Ticket> findByNameAndSurnameAnAndFlightId(@Param("name") String name, @Param("surname") String surname,
                                                                     @Param("flightId") Long flightId);
}
