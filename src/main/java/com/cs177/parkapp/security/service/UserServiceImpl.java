package com.cs177.parkapp.security.service;

import com.cs177.parkapp.security.dto.UserDto;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.repository.RoleRepository;
import com.cs177.parkapp.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

  private UserRepository userRepository;
//  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException
  {
    User user = userRepository.findByEmail(email);
    if(user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
      Collection<Role> roles
  ){
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User save(UserDto userDto) {
    return userRepository.save(userDto.toUser());
  }
}
