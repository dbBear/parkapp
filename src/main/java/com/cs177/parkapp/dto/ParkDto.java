package com.cs177.parkapp.dto;

import com.cs177.parkapp.model.Park;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkDto {

  private Long id;
  @NotEmpty
  private String name;
  private Long rangerId;

  public ParkDto(Park park) {
    this.id = park.getId();
    this.name = park.getName();
    this.rangerId = park.getOfficial().getId();
  }

  public Park newPark() {
    return Park.builder()
        .name(name)
        .build();
  }
}
