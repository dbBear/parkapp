package com.cs177.parkapp.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Ticket extends BaseEntity{

  private String name;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @OneToOne(fetch = FetchType.EAGER)
  private Category category;

  @Lob
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "submitter_id")
//  @Setter(AccessLevel.NONE)
  private Submitter submitter;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "park_id")
//  @Setter(AccessLevel.NONE)
  private Park park;

//  public void setSubmitter(Submitter submitter) {
//    submitter.getTickets().add(this);
//    this.submitter = submitter;
//  }
//
//  public void setPark(Park park) {
//    park.getTickets().add(this);
//    this.park = park;
//  }

}
