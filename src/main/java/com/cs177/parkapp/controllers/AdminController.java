package com.cs177.parkapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cs177.parkapp.config.StaticNames.DEV_DIR;

@AllArgsConstructor
@RequestMapping({"/admin"})
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

  @GetMapping({"/new-user"})
  public String getRegistrationForm() {
    return DEV_DIR + "/admin/admin-registration-form";
  }
}
