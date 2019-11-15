package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.converters.TicketCommandToTicket;
import com.cs177.parkapp.converters.TicketToTicketCommand;
import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.services.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final TicketCommandToTicket ticketCommandToTicket;
  private final TicketToTicketCommand ticketToTicketCommand;

  public TicketServiceImpl(
      TicketRepository ticketRepository,
      TicketCommandToTicket ticketCommandToTicket,
      TicketToTicketCommand ticketToTicketCommand)
  {
    this.ticketRepository = ticketRepository;
    this.ticketCommandToTicket = ticketCommandToTicket;
    this.ticketToTicketCommand = ticketToTicketCommand;
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
  @Transactional
  public TicketCommand saveTicketCommand(TicketCommand ticketCommand) {
    Ticket detachedTicket = ticketCommandToTicket.convert(ticketCommand);
    Ticket savedTicket = ticketRepository.save(detachedTicket);
    return ticketToTicketCommand.convert(savedTicket);
  }

  @Override
  @Transactional
  public TicketCommand findCommandById(Long id) {
    return ticketToTicketCommand.convert(this.findBydId(id));
  }
}
