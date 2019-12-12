package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.cs177.parkapp.config.StaticStrings.*;


@Slf4j
@RequestMapping({"/tickets"})
@Controller
public class TicketController {

  private final TicketService ticketService;
  private final CategoryService categoryService;
  private final ParkService parkService;
  private final RangerService rangerService;
  private final SubmitterService submitterService;
  private final AuthenticationFacade authenticationFacade;

  public TicketController(
      @Lazy TicketService ticketService,
      @Lazy CategoryService categoryService,
      @Lazy ParkService parkService,
      @Lazy RangerService rangerService,
      @Lazy SubmitterService submitterService,
      AuthenticationFacade authenticationFacade
  ) {
    this.ticketService = ticketService;
    this.categoryService = categoryService;
    this.parkService = parkService;
    this.rangerService = rangerService;
    this.submitterService = submitterService;
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping({"/new"})
  public String newTicket(Model model) {
    Ticket ticket = Ticket.builder().build();
    model.addAttribute("ticket", ticket);
    model.addAttribute("categories", categoryService.getCategories());
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/tickets/ticket-form";
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
    return DEV_DIR + "/tickets/ticket-list";
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
    return DEV_DIR + "/tickets/ticket-form";
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
      @ModelAttribute Ticket ticket,
      BindingResult result
  ) {
    Ticket ticketSaved = ticketService.save(ticket);
    if(authenticationFacade.getName().equalsIgnoreCase(ANONYMOUS_NAME)) {
      return "redirect:/?saved=true";
    } else {
      return "redirect:/tickets?saved=true";
    }
  }

  @PostMapping({"/update"})
  public String updateTicket(
      @ModelAttribute Ticket ticket,
      BindingResult result
  ){
    ticketService.save(ticket);
    return "redirect:/tickets?saved=true";
  }


//  private Set<String> getStringAuthorities(Authentication authentication)
}
