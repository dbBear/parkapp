package com.cs177.parkapp.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialCommand {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private ParkCommand park;
}
