package com.cs177.parkapp.controllers;

import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/rangers"})
@AllArgsConstructor
@Controller
public class RangerController {

  private final RangerService rangerService;

  @GetMapping({"", "/"})
  public ModelAndView showRangers(Model model) {
    ModelAndView mv = new ModelAndView("backEndStuff/rangers/rangerList");
    mv.addObject("rangers", rangerService.getRangers());
    return mv;
  }
}
