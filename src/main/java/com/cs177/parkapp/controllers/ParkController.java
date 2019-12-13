package com.cs177.parkapp.controllers;

import com.cs177.parkapp.dto.ParkDto;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.cs177.parkapp.config.StaticStrings.DEV_DIR;
import static com.cs177.parkapp.config.StaticStrings.ROLE_OFFICIAL;

@Slf4j
@Controller
@RequestMapping({"/parks"})
@PreAuthorize("hasRole('ROLE_OFFICIAL') or hasRole('ROLE_ADMIN')")
public class ParkController {


  private final ParkService parkService;
  private final RangerService rangerService;
  private final AuthenticationFacade authenticationFacade;

  public ParkController(
      @Lazy ParkService parkService,
      @Lazy RangerService rangerService,
      AuthenticationFacade authenticationFacade
  ) {
    this.parkService = parkService;
    this.rangerService = rangerService;
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping({"", "/" })
  public String showParks(Model model) {
    model.addAttribute("parks", parkService.findAll());
    return DEV_DIR + "/parks/park-list";
  }

  @GetMapping({"/new"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String newPark(
      Model model
  ) {
    model.addAttribute("park", new ParkDto());
    return DEV_DIR + "/parks/park-form";
  }

  @GetMapping("/update")
  public String updatePark(
      @RequestParam(required = false) Long id,
      Model model
  ){
    boolean isOfficial =
        authenticationFacade.getRoles().contains(ROLE_OFFICIAL);
    Park park;

    if(isOfficial) {
      Ranger ranger = rangerService.findByEmail(authenticationFacade.getName());
      park = parkService.findById(ranger.getPark().getId());
    } else {
      park = parkService.findById(id);
      model.addAttribute("rangers", rangerService.findByPark(park));
    }

    model.addAttribute("park", new ParkDto(park));
    return DEV_DIR + "/parks/park-form";
  }

  @GetMapping("/delete")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deletePark(
      @RequestParam Long id
  ){
    Park park = parkService.findById(id);
    parkService.delete(park);
    return "redirect:/parks";
  }

  @PostMapping({"/new"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String saveOrUpdatePark(
      @ModelAttribute ParkDto parkDto
  ) {
    Park park = parkDto.newPark();
    parkService.save(park);
    return "redirect:/rangers/new?newParkId=" + park.getId();
  }

  @PostMapping({"/update"})
  public String updatePark(
      @ModelAttribute ParkDto parkDto
  ){
    Park park = parkService.findById(parkDto.getId());
    park.setName(parkDto.getName());

    if(park.getOfficial() != null &&
        parkDto.getOfficialId() != null &&
        !park.getOfficial().getId().equals(parkDto.getOfficialId()))
    {
      Ranger newOfficial = rangerService.findById(parkDto.getOfficialId());
      Ranger oldOfficial = park.getOfficial();
      park.setOfficial(newOfficial);
      rangerService.switchOfficialRole(oldOfficial, newOfficial);
    }

    parkService.save(park);
    if(authenticationFacade.getRoles().contains(ROLE_OFFICIAL)){
      return "redirect:/?saved=true";
    }
    return "redirect:/parks";
  }
}
