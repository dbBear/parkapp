package com.cs177.parkapp.dto;

import com.cs177.parkapp.model.Status;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TicketDto {

  private Long id;
  private String name;
  private Status status;
  private CategoryDto categoryDto;
  private String description;
  private SubmitterDto submitterDto;
  private ParkDto parkDto;

}
