package com.cs177.parkapp.model;

import com.cs177.parkapp.security.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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

//  private String firstName;
//  private String lastName;
//  private String email;

  @OneToOne
  private User user;

  @OneToMany(
      mappedBy = "submitter",
      cascade = CascadeType.ALL
  )
  @Builder.Default
  private Set<Ticket> tickets = new HashSet<>();

  public String getFullName() {
    return user.getFirstName() + " " + user.getLastName();
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
