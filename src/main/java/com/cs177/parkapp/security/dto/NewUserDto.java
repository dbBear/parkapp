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
public class NewUserDto {

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


}
