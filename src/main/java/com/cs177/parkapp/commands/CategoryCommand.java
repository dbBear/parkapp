package com.cs177.parkapp.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class CategoryCommand {
  private Long id;
  private String name;
  private String description;
}
