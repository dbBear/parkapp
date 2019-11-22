package com.cs177.parkapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.cs177.parkapp.controllers.StaticStuff.DEV_DIR;

@RequestMapping("/login")
@Controller
public class LoginController {

  @GetMapping({"","/"})
  public ModelAndView getLoginPage(Model model) {
    ModelAndView mav = new ModelAndView(DEV_DIR + "/login/loginForm");
    return mav;
  }
}
