package com.cs177.parkapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
  private Long id;
  private String name;
  private String description;

  @Builder
  public CategoryCommand(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
