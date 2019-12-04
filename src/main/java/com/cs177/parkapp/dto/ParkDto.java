package com.cs177.parkapp.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ParkDto {

  private Long id;
  private String name;
  private Set<RangerDto> rangerDtos = new HashSet<>();
  private Set<TicketDto> ticketDtos = new HashSet<>();

}
