package com.cs177.parkapp.commands;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitterCommand {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  @Builder.Default
  private Set<TicketCommand> tickets = new HashSet<>();

}
