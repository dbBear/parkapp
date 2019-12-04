package com.cs177.parkapp.security.entity;

import com.cs177.parkapp.model.Ranger;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private boolean enabled = true;

  @ManyToMany(
      fetch = FetchType.EAGER
//      cascade = CascadeType.ALL
  )
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")
      )
  @Builder.Default
  private Collection<Role> roles = new ArrayList<>();

  public User(String firstName, String lastName, String email,
              String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public User(String firstName, String lastName,
              String email, String password, Collection<Role> roles
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }


  public void addRoles(Collection<Role> rolesAdding) {
    roles.addAll(rolesAdding);
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public void removeRole(Role role) {
    Role roleToRemove = roles.stream()
        .filter(r -> r.getId().equals(role.getId()))
        .findFirst()
        .orElse(null);
    roles.remove(roleToRemove);
  }
}
