package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.TicketNotFoundException;
import com.cs177.parkapp.model.Status;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.repositories.SubmitterRepository;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.security.repository.UserRepository;
import com.cs177.parkapp.security.service.UserService;
import com.cs177.parkapp.services.SubmitterService;
import com.cs177.parkapp.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_EMAIL;
import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_NAME;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final SubmitterService submitterService;
  private final AuthenticationFacade authenticationFacade;

  @Override
  public Set<Ticket> getTickets() {
    return new HashSet<Ticket>(ticketRepository.findAll());
  }

  @Override
  public Ticket findBydId(Long id) {
    return ticketRepository.findById(id)
        .orElseThrow(() ->
            new EmailNotFoundException("Ticket id:" + id + " not found")
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
      return ticketRepository.save(ticket);
    }
    Ticket ticketFound = ticketRepository.findById(ticket.getId())
        .orElseThrow(() ->
            new TicketNotFoundException("Ticket id: " + ticket.getId() + " not found.")
        );

    ticketFound.setName(ticket.getName());
    ticketFound.setStatus(ticket.getStatus());
    ticketFound.setCategory(ticket.getCategory());
    ticketFound.setDescription(ticket.getDescription());
    ticketFound.setPark(ticket.getPark());
    return ticketRepository.save(ticketFound);
  }

  @Override
  public void delete(Ticket ticket) {
    ticketRepository.delete(ticket);
  }
}
