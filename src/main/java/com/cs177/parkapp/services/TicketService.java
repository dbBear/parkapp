package com.cs177.parkapp.services;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;

import java.util.Set;

public interface TicketService {
  Set<Ticket> findAll();
  Ticket findBydId(Long id);
  Ticket save(Ticket ticket);
  void delete(Ticket ticket);
  Set<Ticket> findAllByPark(Park park);
  Set<Ticket> findAllBySubmitter(Submitter submitter);
  Set<Ticket> findAllByCategory(Category category);
  Set<Ticket> findAllByParkAndCategory(Park park, Category category);
}
