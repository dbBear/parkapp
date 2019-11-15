package com.cs177.parkapp.commands;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCommand {
  private Long id;
  private String name;
  private CategoryCommand category;
  private Date date;
  private String description;
  private SubmitterCommand submitter;
  private ParkCommand park;
}
