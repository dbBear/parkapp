package com.cs177.parkapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter()
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Official extends BaseEntity{

  private String firstName;
  private String lastName;
  private String email;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "park_id")
  private Park park;

  @Builder
  public Official(Long id, String firstName, String lastName, String email,
                  Park park) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.park = park;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }
}
