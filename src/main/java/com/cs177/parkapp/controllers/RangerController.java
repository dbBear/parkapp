package com.cs177.parkapp.controllers;

import com.cs177.parkapp.dto.NewRangerDto;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.security.service.UserService;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticNames.DEV_DIR;
import static com.cs177.parkapp.config.StaticNames.ROLE_OFFICIAL;

@AllArgsConstructor
@Controller
@RequestMapping({"/rangers"})
@PreAuthorize("hasRole('ROLE_OFFICIAL') or hasRole('ROLE_ADMIN')")
public class RangerController {

  private final RangerService rangerService;
  private final ParkService parkService;
  private final UserService userService;
  private final AuthenticationFacade authenticationFacade;

  @GetMapping({"", "/"})
  public String showRangers(Model model) {
    Set<Ranger> rangers;
    if(authenticationFacade.getRoles().contains(ROLE_OFFICIAL)) {
      Ranger ranger = rangerService.findByEmail(authenticationFacade.getName());
      rangers = rangerService.findByPark(ranger.getPark());
    } else {
      rangers = rangerService.findAll();
    }
    model.addAttribute("rangers", rangers);
    return DEV_DIR + "/rangers/rangerList";
  }

  @GetMapping({"/new"})
  public String newRanger(Model model) {
    model.addAttribute("ranger", new NewRangerDto());

    Set<Park> parks;
    if(authenticationFacade.getRoles().contains(ROLE_OFFICIAL)) {
      Ranger ranger = rangerService.findByEmail(authenticationFacade.getName());
      parks = new HashSet<>(Arrays.asList(
          parkService.findById(ranger.getPark().getId())
      ));
    } else {
      parks = parkService.findAll();
    }

    model.addAttribute("parks", parks);
    return DEV_DIR + "/rangers/new-ranger-form";
  }

  @GetMapping({"/update"})
  public String updateRanger(
      @RequestParam Long id,
      Model model
  ){
    model.addAttribute("ranger", rangerService.findById(id));
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/rangers/update-ranger-form";
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
      @ModelAttribute("ranger") @Valid NewRangerDto rangerDto,
      BindingResult result
  ){
    User userExisting = userService.testNewEmail(rangerDto.getEmail());
    if(userExisting.getId() != null) {
      result.rejectValue("email", "There is already an account registered " +
          "with this email.");
    }
    if(result.hasErrors()) {
      return DEV_DIR + "/ranger/new-ranger-form";
    }
    User userNew = userService.newSave(rangerDto);
    rangerService.newSave(userNew, rangerDto);

    return "redirect:/rangers?saved=true";
  }
}
