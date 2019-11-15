package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.services.TicketService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  public TicketServiceImpl(
      TicketRepository ticketRepository)
  {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public Set<Ticket> getTickets() {
    return new HashSet<Ticket>(ticketRepository.findAll());
  }

  @Override
  public Ticket findBydId(Long id) {
    return ticketRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException("Ticket id:" + id + " not found")
        );
  }

  @Override
  public Ticket saveTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }
}
