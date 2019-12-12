package com.cs177.parkapp.security.service;

import com.cs177.parkapp.exceptions.AnonymousNotFound;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.security.dto.NewUserDto;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cs177.parkapp.config.StaticStrings.ANONYMOUS_EMAIL;
import static com.cs177.parkapp.config.StaticStrings.ROLE_USER;

@AllArgsConstructor
//@Transactional
@Service
public class UserServiceImpl implements UserService{

  private UserRepository userRepository;
  private RoleService roleService;
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException
  {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() ->
            new UsernameNotFoundException("Invalid username or password")
        );

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
  public User getAnonymousUser() {
    return userRepository.findByEmail(ANONYMOUS_EMAIL)
        .orElseThrow(AnonymousNotFound::new);
  }

  @Override
  public User findByEmail(String email) {
    //todo better error checking between this and registration controller
    return userRepository.findByEmail(email)
        .orElseThrow(() ->
            new EmailNotFoundException("User email: " + email + " not found")
        );
  }

  @Override
  public User testNewEmail(String email) {
    return userRepository.findByEmail(email)
        .orElse(new User());
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("User id: " + id + " not found")
        );
  }

  @Override
  public User findBySubmitter(Submitter submitter) {
    return userRepository.findBySubmitter(submitter)
        .orElseThrow(() ->
            new IdNotFoundException("User with submitter id:"
                + submitter.getId() + " not found")
        );
  }

  @Override
  public User findByRanger(Ranger ranger) {
    return userRepository.findByRanger(ranger)
        .orElseThrow(() ->
            new IdNotFoundException("Ranger with user id:"
                + ranger.getId() + " not found")
        );
  }

  @Override
  public User newSave(NewUserDto newUserDto) {
    User user = User.builder()
        .firstName(newUserDto.getFirstName())
        .lastName(newUserDto.getLastName())
        .email(newUserDto.getEmail())
        .password(passwordEncoder.encode(newUserDto.getPassword()))
        .enabled(true)
        .build();
    user.getRoles().add(
        roleService.findByName(ROLE_USER)
    );
    return userRepository.save(user);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User addRoles(User user, Set<Role> roles) {
    roles.forEach(user::addRole);
    userRepository.save(user);
    return user;
  }

  @Override
  public User removeRoles(User user, Set<Role> roles) {
    roles.forEach(user::removeRole);
    userRepository.save(user);
    return user;
  }

}
