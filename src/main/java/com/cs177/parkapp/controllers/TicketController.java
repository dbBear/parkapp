package com.cs177.parkapp.controllers;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.commands.ParkCommand;
import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@RequestMapping({"/ticket"})
@Controller
public class TicketController {

  private final TicketService ticketService;
  private final CategoryService categoryService;
  private final ParkService parkService;

  public TicketController(
      TicketService ticketService,
      CategoryService categoryService,
      ParkService parkService
  )
  {
    this.ticketService = ticketService;
    this.categoryService = categoryService;
    this.parkService = parkService;
  }

  @GetMapping({"", "/", "/tickets"})
  public String showTickets(Model model) {
    model.addAttribute("tickets", ticketService.getTickets());
    return "tickets/list";
  }

  @GetMapping({"/new"})
  public String newTicket(Model model) {
    TicketCommand ticketCommand = TicketCommand.builder()
        .category(new CategoryCommand())
        .park(new ParkCommand())
        .build();
    model.addAttribute("ticket", new TicketCommand());
    model.addAttribute("categories", categoryService.getCategories());
    model.addAttribute("parks", parkService.getParks());
    return "tickets/ticketForm";
  }

  @PostMapping({"/new"})
  public String saveTicket(
      @ModelAttribute TicketCommand ticketCommand,
      BindingResult result)
  {
    ticketCommand.setDate(new Date());
    TicketCommand savedTicketCommand =
        ticketService.saveTicketCommand(ticketCommand);
    return "redirect:/ticket";
  }
}
