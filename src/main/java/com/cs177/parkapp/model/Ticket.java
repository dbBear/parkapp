package com.cs177.parkapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Ticket extends BaseEntity{

  private String name;
  @OneToOne(fetch = FetchType.EAGER)
  private Category category;
  @Lob
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "submitter_id")
  private Submitter submitter;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "park_id")
  private Park park;

  @Builder
  public Ticket(
      Long id,
      Category category,
      String name,
      Date date,
      String description,
      Submitter submitter,
      Park park
  ) {
    super(id);
    this.category = category;
    this.name = name;
    this.description = description;
    this.submitter = submitter;
    this.park = park;
  }
}
