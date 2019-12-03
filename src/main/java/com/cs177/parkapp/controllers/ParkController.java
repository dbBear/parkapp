package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.services.ParkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.cs177.parkapp.config.StaticStuff.DEV_DIR;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping({"/parks"})
@PreAuthorize("hasRole('ROLE_OFFICIAL')")
public class ParkController {

  private final ParkService parkService;

  @GetMapping({"", "/" })
  public String showParks(Model model) {
    model.addAttribute("parks", parkService.getParks());
    return DEV_DIR + "/parks/parkList";
  }

  @GetMapping("/new")
  public ModelAndView newPark() {
    ModelAndView mv = new ModelAndView(DEV_DIR + "/parks/parkForm");
    mv.addObject(new Park());
    return mv;
  }

  @GetMapping("/update")
  public String updatePark(
      @RequestParam(required = false) String id,
      Model model
  ){
    Park park;
    if(id == null) {
      park = new Park();
    } else {
      park = parkService.findById(Long.valueOf(id));
    }
    model.addAttribute(park);
    return DEV_DIR + "/parks/parkForm";
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
