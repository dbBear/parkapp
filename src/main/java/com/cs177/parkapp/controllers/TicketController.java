package com.cs177.parkapp.controllers;

import com.cs177.parkapp.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/ticket"})
@Controller
public class TicketController {

  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @GetMapping({"", "/", "/tickets"})
  public String showTickets(Model model) {
    model.addAttribute("tickets", ticketService.getTickets());
    return "tickets/list";
  }
}
