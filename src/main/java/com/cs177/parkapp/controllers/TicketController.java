package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

import static com.cs177.parkapp.config.StaticNames.*;


@Slf4j
@RequestMapping({"/tickets"})
@AllArgsConstructor
@Controller
public class TicketController {

  private final TicketService ticketService;
  private final CategoryService categoryService;
  private final ParkService parkService;
  private final RangerService rangerService;
  private final SubmitterService submitterService;
  private final AuthenticationFacade authenticationFacade;

  @GetMapping({"/new"})
  public String newTicket(Model model) {
    Ticket ticket = Ticket.builder().build();
    model.addAttribute("ticket", ticket);
    model.addAttribute("categories", categoryService.getCategories());
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/tickets/ticketForm";
  }

  @GetMapping({"", "/" })
  @PreAuthorize("isAuthenticated()")
  public String showTickets(Model model ) {

    Set<Ticket> tickets;
    if(authenticationFacade.getRoles().contains(ROLE_ADMIN)) {
      tickets = ticketService.findAll();
    } else if(authenticationFacade.getRoles().contains(ROLE_RANGER)) {
      Ranger ranger = rangerService.findByEmail(authenticationFacade.getName());
      tickets = ticketService.findAllByPark(ranger.getPark());
    } else {
      Submitter submitter =
          submitterService.findByEmail(authenticationFacade.getName());
      tickets = ticketService.findAllBySubmitter(submitter);
    }
    model.addAttribute("tickets", tickets);
    return DEV_DIR + "/tickets/ticketList";
  }

  @GetMapping({"/update"})
  @PreAuthorize("isAuthenticated()")
  public String updateTicket(
      @RequestParam Long id,
      Model model
  ){
    model.addAttribute("ticket", ticketService.findById(id));
    model.addAttribute("categories", categoryService.getCategories());
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/tickets/ticketForm";
  }

  @GetMapping({"/delete"})
  @PreAuthorize("hasRole('ROLE_OFFICIAL') or hasRole('ROLE_ADMIN')")
  public String deleteTicket(
      @RequestParam Long id
  ){
    ticketService.delete(ticketService.findById(id));
    return "redirect:/tickets?delete=true";
  }

  @PostMapping({"/new"})
  public String saveTicket(
      Ticket ticket,
      BindingResult result
  ) {
    Ticket ticketSaved = ticketService.save(ticket);
    if(authenticationFacade.getName().equalsIgnoreCase(ANONYMOUS_NAME)) {
      return "redirect:/?saved=true";
    } else {
      return "redirect:/tickets?saved=true";
    }
  }


//  private Set<String> getStringAuthorities(Authentication authentication)
}
