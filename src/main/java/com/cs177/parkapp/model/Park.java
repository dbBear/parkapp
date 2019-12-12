package com.cs177.parkapp.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"official", "rangers",
    "tickets"})
@ToString(exclude = {"official", "rangers", "tickets"})
@Entity
public class Park extends BaseEntity {

  private String name;
  @OneToOne
  @Nullable
  private Ranger official;

  @OneToMany(
      mappedBy = "park",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @Builder.Default
  private Set<Ranger> rangers = new HashSet<>();

  @OneToMany(
      mappedBy = "park",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
  )
  @Builder.Default
  private Set<Ticket> tickets = new HashSet<>();

  public void addRanger(Ranger ranger) {
    ranger.setPark(this);
    rangers.add(ranger);
  }

  public void removeRanger(Ranger ranger) {
    // todo better error checking
    Ranger rangerToRemove = rangers.stream()
        .filter(r -> r.getId().equals(ranger.getId()))
        .findFirst()
        .orElse(null);
    if(rangerToRemove != null) {
      rangers.remove(rangerToRemove);
      rangerToRemove.setPark(null);
    }

  }

  public void addTicket(Ticket ticket) {
    ticket.setPark(this);
    tickets.add(ticket);
  }

  public void removeTicket(Ticket ticket) {
    // todo better error checking
    Ticket ticketToRemove = tickets.stream()
        .filter(t -> t.getId().equals(ticket.getId()))
        .findFirst()
        .orElse(null);
    if(ticketToRemove != null) {
      tickets.remove(ticketToRemove);
      ticketToRemove.setPark(null);
    }

  }

}
