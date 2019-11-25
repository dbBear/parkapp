package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.SubmitterService;
import com.cs177.parkapp.services.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cs177.parkapp.controllers.StaticStuff.DEV_DIR;


@Slf4j
@RequestMapping({"/tickets"})
@AllArgsConstructor
@Controller
public class TicketController {

  private final TicketService ticketService;
  private final CategoryService categoryService;
  private final ParkService parkService;
  private final SubmitterService submitterService;

  @GetMapping({"", "/" })
  public String showTickets(Model model) {
    model.addAttribute("tickets", ticketService.getTickets());
    return DEV_DIR + "/tickets/ticketList";
  }

  @GetMapping({"/new"})
  public String newTicket(Model model) {
    Ticket ticket = Ticket.builder()
        .build();

    model.addAttribute("ticket", ticket);
    model.addAttribute("categories", categoryService.getCategories());
    model.addAttribute("parks", parkService.getParks());
    return DEV_DIR + "/tickets/ticketForm";
  }

  @PostMapping({"/new"})
  public String saveTicket(
      Ticket ticket,
      BindingResult result)
  {
    log.debug(result.toString());
    if(ticket.getSubmitter() == null) {
      ticket.setSubmitter(submitterService.findByEmail("Anonymous.Anonymous" +
          "@email.com"));
    }
    Ticket ticketSaved = ticketService.save(ticket);
    return "redirect:/tickets";
  }
}
