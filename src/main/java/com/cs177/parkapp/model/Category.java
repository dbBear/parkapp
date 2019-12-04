package com.cs177.parkapp.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"tickets"})
@ToString(exclude = {"tickets"})
@Entity
public class Category extends BaseEntity{

  private String name;
  private String description;

  @OneToMany(
      mappedBy = "category"
  )
  @Builder.Default
  private Set<Ticket> tickets = new HashSet<>();

  public void addTicket(Ticket ticket) {
    tickets.add(ticket);
    ticket.setCategory(this);
  }

  public void removeTicket(Ticket ticket) {
    Ticket ticketToRemove = tickets.stream()
        .filter(t -> t.getId().equals(ticket.getId()))
        .findFirst()
        .orElse(null);
    ticketToRemove.setCategory(null);
    tickets.remove(ticketToRemove);
  }




}
