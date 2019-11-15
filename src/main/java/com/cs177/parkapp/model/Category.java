package com.cs177.parkapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Category extends BaseEntity{

  private String name;
  private String description;

  @Builder
  public Category(Long id, String name, String description) {
    super(id);
    this.name = name;
    this.description = description;
  }
}
