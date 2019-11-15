package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
