package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.mail.MailClient;
import com.cs177.parkapp.model.*;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final SubmitterService submitterService;
  private final AuthenticationFacade authenticationFacade;
  private final MailClient mailClient;

  public TicketServiceImpl(
      TicketRepository ticketRepository,
      @Lazy SubmitterService submitterService,
      AuthenticationFacade authenticationFacade,
      MailClient mailClient
  ) {
    this.ticketRepository = ticketRepository;
    this.submitterService = submitterService;
    this.authenticationFacade = authenticationFacade;
    this.mailClient = mailClient;
  }

  @Override
  public Set<Ticket> findAll() {
    return new HashSet<Ticket>(ticketRepository.findAll());
  }

  @Override
  public Ticket findById(Long id) {
    return ticketRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("Ticket id:" + id + " not found")
        );
  }

  @Override
  public Ticket save(Ticket ticket) {
    if(ticket.getId() == null) {
      String currentUser = authenticationFacade.getAuthentication().getName();
      ticket.setStatus(Status.OPEN);
      Submitter submitter = submitterService.findByEmail(currentUser);
      ticket.setSubmitter(submitter);
//      ticket.setSubmitter(submitterService.findByEmail(currentUser));
      Ticket ticketSaved = ticketRepository.save(ticket);
      mailClient.sendNewTicket(ticketSaved);
      return  ticketSaved;
    }

    Ticket ticketFound = ticketRepository.findById(ticket.getId())
        .orElseThrow(() ->
            new IdNotFoundException("Ticket id: " + ticket.getId() + " not found.")
        );

    ticketFound.setName(ticket.getName());
    ticketFound.setStatus(ticket.getStatus());
    ticketFound.setCategory(ticket.getCategory());
    ticketFound.setDescription(ticket.getDescription());
    ticketFound.setPark(ticket.getPark());

    Ticket ticketSaved = ticketRepository.save(ticketFound);
    if(ticket.getStatus().equals(Status.CLOSED)) {
      mailClient.sendClosedTicket(ticketSaved);
    }
    return ticketSaved;
  }

  @Override
  public void delete(Ticket ticket) {
    ticketRepository.delete(ticket);
  }

  @Override
  public Set<Ticket> findAllByPark(Park park) {
    return new HashSet<>(ticketRepository.findByPark(park));
  }

  @Override
  public Set<Ticket> findAllBySubmitter(Submitter submitter) {
    return new HashSet<>(ticketRepository.findBySubmitter(submitter));
  }

  @Override
  public Set<Ticket> findAllByCategory(Category category) {
    return new HashSet<>(ticketRepository.findByCategory(category));
  }

  @Override
  public Set<Ticket> findAllByParkAndCategory(Park park, Category category) {
    return new HashSet<>(
        ticketRepository.findByParkAndCategory(park, category)
    );
  }
}
