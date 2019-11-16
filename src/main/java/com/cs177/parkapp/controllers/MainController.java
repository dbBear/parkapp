package com.cs177.parkapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
//@RequestMapping({"", "/"})
@Controller
public class MainController {

  @GetMapping({"", "/"})
  public String getIndex() {
    return "backEndStuff/index";
  }
}
