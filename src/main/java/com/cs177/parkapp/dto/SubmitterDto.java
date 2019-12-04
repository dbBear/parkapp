package com.cs177.parkapp.dto;

import com.cs177.parkapp.security.dto.UserDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SubmitterDto {

  private Long id;
  private UserDto userDto;
  private Set<TicketDto> ticketDtos = new HashSet<>();

}
