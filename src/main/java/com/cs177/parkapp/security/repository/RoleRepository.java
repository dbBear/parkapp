package com.cs177.parkapp.security.repository;

import com.cs177.parkapp.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
