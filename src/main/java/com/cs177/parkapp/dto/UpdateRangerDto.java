package com.cs177.parkapp.dto;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.constraint.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch.List({
    @FieldMatch(first = "email", second = "confirmEmail",
        message = "The emails must match")
})
public class UpdateRangerDto {

  @NotNull
  private Long id;
  @NotNull
  private Long parkId;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  private String email;
  @NotEmpty
  private String confirmEmail;

  public UpdateRangerDto(Ranger ranger) {
    this.id = ranger.getId();
    this.parkId = ranger.getPark().getId();
    this.firstName = ranger.getUser().getFirstName();
    this.lastName = ranger.getUser().getLastName();
    this.email = ranger.getUser().getEmail();
  }
}
