package com.cs177.parkapp.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = "tickets")
@ToString(exclude = {"tickets"})
@Entity
public class Submitter extends BaseEntity {

  private String firstName;
  private String lastName;
  private String email;

  @OneToMany(
      mappedBy = "submitter",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @Builder.Default
  private Set<Ticket> tickets = new HashSet<>();

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public void addTicket(Ticket ticket) {
    ticket.setSubmitter(this);
    tickets.add(ticket);
  }

  public void removeTicket(Ticket ticket) {
    Ticket ticketToRemove = tickets.stream()
        .filter(t -> t.getId().equals(ticket.getId()))
        .findFirst()
        .orElse(null);
    ticketToRemove.setSubmitter(null);
    tickets.remove(ticketToRemove);
  }
}
