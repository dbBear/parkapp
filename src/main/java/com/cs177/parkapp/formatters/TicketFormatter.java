package com.cs177.parkapp.formatters;

import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@AllArgsConstructor
@Component
public class TicketFormatter implements Formatter<Ticket> {

  private final TicketService ticketService;

  @Override
  public Ticket parse(String s, Locale locale) throws ParseException {
    return ticketService.findById(Long.parseLong(s));
  }

  @Override
  public String print(Ticket ticket, Locale locale) {
    return ticket.getName();
  }
}
