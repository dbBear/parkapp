package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.services.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping({"/parks"})
@Controller
public class ParkController {

  private final ParkService parkService;

  public ParkController(ParkService parkService) {
    this.parkService = parkService;
  }

  @GetMapping({"", "/" })
  public String showParks(Model model) {
    model.addAttribute("parks", parkService.getParks());
    return "backEndStuff/parks/parkList";
  }

  @GetMapping("/new")
  public ModelAndView newPark() {
    ModelAndView mv = new ModelAndView("backEndStuff/parks/parkForm");
    mv.addObject(new Park());
    return mv;
  }

  @GetMapping("/update")
  public String updatePark(
      @RequestParam String id,
      Model model
  ){
    Park park = parkService.findById(Long.valueOf(id));
    model.addAttribute(park);
    return "backEndStuff/parks/parkForm";
  }

  @PostMapping("")
  public String saveOrUpdatePark(
      @ModelAttribute Park park
  ) {
    Park savedPark = parkService.save(park);
    return "redirect:/parks";
  }

  @GetMapping("/delete")
  public String deletePark(
      @RequestParam String id
  ){
    Park park = parkService.findById(Long.valueOf(id));
    parkService.delete(park);
    return "redirect:/parks";
  }
}
