package com.cs177.parkapp.formatters;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.services.ParkService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ParkFormatter implements Formatter<Park> {

  private final ParkService parkService;

  public ParkFormatter(ParkService parkService) {
    this.parkService = parkService;
  }

  @Override
  public Park parse(String s, Locale locale) throws ParseException {
    return parkService.findById(Long.parseLong(s));
  }

  @Override
  public String print(Park park, Locale locale) {
    return park.getName();
  }
}
