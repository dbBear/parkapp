package com.cs177.parkapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.cs177.parkapp.config.StaticStrings.DEV_DIR;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String getLoginPage() {
    return DEV_DIR + "/login/loginForm";
  }

  @GetMapping("/logout-success")
  public String getLogoutSuccess() {
    return DEV_DIR + "/index";
  }
}
