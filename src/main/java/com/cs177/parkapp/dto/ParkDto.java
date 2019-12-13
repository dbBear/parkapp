package com.cs177.parkapp.dto;

import com.cs177.parkapp.model.Park;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkDto {

  private Long id;
  @NotEmpty
  private String name;
  private Long officialId;

  public ParkDto(Park park) {
    this.id = park.getId();
    this.name = park.getName();
    if(park.getOfficial() != null) {
      this.officialId = park.getOfficial().getId();
    }
  }


  public Park newPark() {
    return Park.builder()
        .name(name)
        .build();
  }
}
