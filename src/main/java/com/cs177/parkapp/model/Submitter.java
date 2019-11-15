package com.cs177.parkapp.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "tickets")
@NoArgsConstructor
@Entity
public class Submitter extends BaseEntity {

  private String firstName;
  private String lastName;
  private String email;

  @OneToMany(
      mappedBy = "submitter",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Ticket> tickets = new HashSet<>();

  @Builder
  public Submitter(Long id, String firstName, String lastName,String email,
                   Set<Ticket> tickets) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    if(this.tickets == null) {
      this.tickets = tickets;
    }
  }

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
