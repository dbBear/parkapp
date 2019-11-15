package com.cs177.parkapp.controllers;

import com.cs177.parkapp.services.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping({"/park"})
@Controller
public class ParkController {

  private final ParkService parkService;

  public ParkController(ParkService parkService) {
    this.parkService = parkService;
  }

  @GetMapping({"", "/", "/parks"})
  public String showParks(Model model) {
    model.addAttribute("parks", parkService.getParks());
    return "parks/list";
  }
}
