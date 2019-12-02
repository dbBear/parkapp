package com.cs177.parkapp.security.dto;

import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.RoleService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {

  private BCryptPasswordEncoder passwordEncoder;
  private RoleService roleService;

  private Long roleId;
  private String firstName;
  private String lastName;
  private String password;
  private String email;

  public UserDto(User user) {
    firstName = user.getFirstName();
    lastName = user.getLastName();
    email = user.getEmail();
  }

  public User toUser() {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));

    List<Role> roles = roleService.getAll().stream()
        .filter(role -> role.getId() <= roleId)
        .collect(Collectors.toList());
    user.setRoles(roles);
    return user;
  }

}
