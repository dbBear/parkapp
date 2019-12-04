package com.cs177.parkapp.dto;

import com.cs177.parkapp.security.dto.UserDto;
import lombok.Data;

@Data
public class RangerDto {

  private Long id;
  private UserDto userDto;
  private ParkDto parkDto;
}
