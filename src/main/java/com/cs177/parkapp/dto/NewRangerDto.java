package com.cs177.parkapp.dto;

import com.cs177.parkapp.security.dto.NewUserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewRangerDto extends NewUserDto {

  private Long parkId;

}
