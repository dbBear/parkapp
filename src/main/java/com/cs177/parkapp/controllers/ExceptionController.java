package com.cs177.parkapp.controllers;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.exceptions.NameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(EmailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String emailNotFoundException(
      EmailNotFoundException e,
      Model model
  ) {
    model.addAttribute("exceptionName", EmailNotFoundException.TITLE);
    model.addAttribute("exceptionMessage", e.getMessage());
    return "error/exception";
  }

  @ExceptionHandler(IdNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String idNotFoundException(
      IdNotFoundException e,
      Model model
  ){
    model.addAttribute("exceptionName", EmailNotFoundException.TITLE);
    model.addAttribute("exceptionMessage", e.getMessage());
    return "error/exception";
  }

  @ExceptionHandler(NameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String nameNotFoundException(
      NameNotFoundException e,
      Model model
  ){
    model.addAttribute("exceptionName", NameNotFoundException.TITLE);
    model.addAttribute("exceptionMessage", e.getMessage());
    return "error/exception";
  }

}
