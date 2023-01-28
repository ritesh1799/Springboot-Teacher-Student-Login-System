package com.defecttracking.repository;

import com.defecttracking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Query(value = "select a from Ticket a where status =:status")
    Optional<Ticket> findByStatus(@Param("status") String status);
}
