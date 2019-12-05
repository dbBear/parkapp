package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.cs177.parkapp.config.StaticNames.DEV_DIR;

@AllArgsConstructor
@Controller
@RequestMapping({"/rangers"})
@PreAuthorize("hasRole('ROLE_OFFICIAL') or hasRole('ROLE_ADMIN')")
public class RangerController {

  private final RangerService rangerService;
  private final ParkService parkService;

  @GetMapping({"", "/"})
  public String showRangers(Model model) {
    model.addAttribute("rangers", rangerService.findAll());
    return DEV_DIR + "/rangers/rangerList";
  }

  @GetMapping({"/new"})
  public String newRanger(Model model) {
    Ranger ranger = new Ranger();
    model.addAttribute("ranger", ranger);
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/ranger/rangerForm";
  }

  @GetMapping({"/update"})
  public String updateRanger(
      @RequestParam Long id,
      Model model
  ){
    model.addAttribute("ranger", rangerService.findById(id));
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/rangers/rangerForm";
  }

  @GetMapping({"/delete"})
  public String deleteRanger(
      @RequestParam Long id
  ){
    rangerService.delete(rangerService.findById(id));
    return "redirect:/rangers?delete=true";
  }

  @PostMapping({"/new"})
  public String saveRanger(
      Ranger ranger
  ){
    Ranger rangerSaved = rangerService.save(ranger);
    return "redirect:/rangers?saved=true";
  }
}
