package com.cs177.parkapp.commands;

import com.cs177.parkapp.model.Ticket;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkCommand {
  private Long id;
  private String name;
  @Builder.Default
  private Set<OfficialCommand> officials = new HashSet<>();
  @Builder.Default
  private Set<TicketCommand> tickets = new HashSet<>();
}
