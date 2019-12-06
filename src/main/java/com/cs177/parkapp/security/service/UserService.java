package com.cs177.parkapp.security.service;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.security.dto.NewUserDto;
import com.cs177.parkapp.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User getAnonymousUser();
  User findByEmail(String email);
  User testNewEmail(String email);
  User findById(Long id);
  User findBySubmitter(Submitter submitter);
  User findByRanger(Ranger ranger);
  User newSave(NewUserDto newUserDto);
  User save(User user);

}
