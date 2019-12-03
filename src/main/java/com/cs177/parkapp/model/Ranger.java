package com.cs177.parkapp.model;

import com.cs177.parkapp.security.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Ranger extends BaseEntity{

  @OneToOne(
      fetch = FetchType.EAGER
  )
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "park_id")
  private Park park;

  public String getFullName() {
    return user.getFirstName() + " " + user.getLastName();
  }
}
