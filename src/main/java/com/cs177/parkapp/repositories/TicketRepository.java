package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  List<Ticket> findByPark(Park park);
  List<Ticket> findBySubmitter(Submitter submitter);
  List<Ticket> findByCategory(Category category);
  List<Ticket> findByParkAndCategory(Park park, Category category);

}
