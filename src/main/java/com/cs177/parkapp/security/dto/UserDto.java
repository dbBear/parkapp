package com.cs177.parkapp.security.dto;

import com.cs177.parkapp.security.constraint.FieldMatch;
import com.cs177.parkapp.security.constraint.ValidEmail;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword",
        message = "The passwords must match"),
    @FieldMatch(first = "email", second = "confirmEmail",
        message = "The emails must match")
})
public class UserDto {

//  private BCryptPasswordEncoder passwordEncoder;
//  private RoleService roleService;

  private Long roleId;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  private String password;
  @NotEmpty
  private String confirmPassword;
  @NotEmpty
  @ValidEmail
  private String email;
  @NotEmpty
  @ValidEmail
  private String confirmEmail;

//  public UserDto(User user) {
//    firstName = user.getFirstName();
//    lastName = user.getLastName();
//    email = user.getEmail();
//  }
//
//  public User toUser() {
//    User user = new User();
//    user.setFirstName(firstName.toLowerCase());
//    user.setLastName(lastName.toLowerCase());
//    user.setEmail(email.toLowerCase());
//    user.setPassword(passwordEncoder.encode(password));
//
//    List<Role> roles = roleService.getAll().stream()
//        .filter(role -> role.getId() <= roleId)
//        .collect(Collectors.toList());
//    user.setRoles(roles);
//    return user;
//  }

}
