package com.cs177.parkapp.services;

import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.model.Ticket;

import java.util.Set;

public interface TicketService {
  Set<Ticket> getTickets();
  Ticket findBydId(Long id);
  TicketCommand saveTicketCommand(TicketCommand ticketCommand);
  TicketCommand findCommandById(Long id);
}
