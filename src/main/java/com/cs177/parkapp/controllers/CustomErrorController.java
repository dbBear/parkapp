package com.cs177.parkapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping({"/error"})
  public String handleError(
      HttpServletRequest req,
      HttpServletResponse res,
      Model model
  ){

    HttpStatus status =
        Stream.of(HttpStatus.values())
            .filter(s -> s.value() == res.getStatus())
            .findFirst()
            .orElse(HttpStatus.NOT_FOUND);

    model.addAttribute("errorCode", status.value());
    model.addAttribute("errorMessage", status.getReasonPhrase());
    return "error/error";
  }


  @Override
  public String getErrorPath() {
    return "/error";
  }
}
