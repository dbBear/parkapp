package com.cs177.parkapp.formatters;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

@AllArgsConstructor
public class RangerFormatter implements Formatter<Ranger> {

  private final RangerService rangerService;

  @Override
  public Ranger parse(String s, Locale locale) throws ParseException {
    return rangerService.findById(Long.parseLong(s));
  }

  @Override
  public String print(Ranger ranger, Locale locale) {
    return ranger.getFullName();
  }
}
