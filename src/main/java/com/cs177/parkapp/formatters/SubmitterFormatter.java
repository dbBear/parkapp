package com.cs177.parkapp.formatters;

import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.services.SubmitterService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@AllArgsConstructor
@Component
public class SubmitterFormatter implements Formatter<Submitter> {

  private final SubmitterService submitterService;

  @Override
  public Submitter parse(String s, Locale locale) throws ParseException {
    return submitterService.findById(Long.parseLong(s));
  }

  @Override
  public String print(Submitter submitter, Locale locale) {
    return submitter.getUser().getEmail();
  }
}
