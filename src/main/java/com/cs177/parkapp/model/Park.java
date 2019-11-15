package com.cs177.parkapp.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"officials", "tickets"})
//@ToString
@ToString(exclude = {"officials", "tickets"})
@Entity
public class Park extends BaseEntity {

  private String name;
  @OneToMany(
      mappedBy = "park",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Official> officials = new HashSet<>();

  @OneToMany(
      mappedBy = "park",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<Ticket> tickets = new HashSet<>();

  @Builder
  public Park(
      Long id,
      String name,
      Set<Official> officials,
      Set<Ticket> tickets
  ) {
    super(id);
    this.name = name;
    if (this.officials == null) {
      this.officials = officials;
    }
    if (this.tickets == null) {
      this.tickets = tickets;
    }

  }

  public void addOfficial(Official official) {
    official.setPark(this);
    officials.add(official);
  }

  public void removeOfficial(Official official) {
    Official officialToRemove = officials.stream()
        .filter(o -> o.getId().equals(official.getId()))
        .findFirst()
        .orElse(null);
    officials.remove(officialToRemove);
    officialToRemove.setPark(null);
  }

  public void addTicket(Ticket ticket) {
    ticket.setPark(this);
    tickets.add(ticket);
  }

  public void removeTicket(Ticket ticket) {
    Ticket ticketToRemove = tickets.stream()
        .filter(t -> t.getId().equals(ticket.getId()))
        .findFirst()
        .orElse(null);
    tickets.remove(ticketToRemove);
    ticketToRemove.setPark(null);
  }

}
