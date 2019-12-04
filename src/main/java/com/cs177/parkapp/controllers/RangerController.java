package com.cs177.parkapp.controllers;

import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping({"/rangers"})
@PreAuthorize(
    "hasRole('ROLE_RANGER') " +
        "or hasRole('ROLE_OFFICIAL') " +
        "or hasRole('ROLE_ADMIN')"
)
public class RangerController {

  private final RangerService rangerService;

  @GetMapping({"", "/"})
  public ModelAndView showRangers(Model model) {
    ModelAndView mv = new ModelAndView("backEndStuff/rangers/rangerList");
    mv.addObject("rangers", rangerService.findAll());
    return mv;
  }
}
