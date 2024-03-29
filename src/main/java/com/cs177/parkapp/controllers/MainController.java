package com.cs177.parkapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cs177.parkapp.config.StaticStrings.DEV_DIR;

@Slf4j
@RequestMapping({"", "/","/index"})
@Controller
public class MainController {

  @GetMapping
  public String getIndex() {
    return DEV_DIR + "/index";
  }
}
