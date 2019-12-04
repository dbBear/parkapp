package com.cs177.parkapp.security.service;

import com.cs177.parkapp.security.dto.UserDto;
import com.cs177.parkapp.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User getAnonymousUser();
  User findByEmail(String email);
  User findById(Long id);
  User save(UserDto userDto);

}
