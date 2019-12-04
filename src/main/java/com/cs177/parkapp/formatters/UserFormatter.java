package com.cs177.parkapp.formatters;

import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@AllArgsConstructor
@Component
public class UserFormatter implements Formatter<User> {

  private final UserService userService;

  @Override
  public User parse(String s, Locale locale) throws ParseException {
    return userService.findById(Long.parseLong(s));
  }

  @Override
  public String print(User user, Locale locale) {
    return user.getEmail();
  }
}
