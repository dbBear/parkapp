package com.cs177.parkapp.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @Lob
  private String description;

  @Nullable
  private Double latitude;
  @Nullable
  private Double longitude;


  @Lob
  private String location;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "submitter_id")
  private Submitter submitter;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "park_id")
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
