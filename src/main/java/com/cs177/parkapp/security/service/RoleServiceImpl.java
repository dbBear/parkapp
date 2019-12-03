package com.cs177.parkapp.security.service;

import com.cs177.parkapp.exceptions.RoleNotFoundException;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

  RoleRepository roleRepository;

  @Override
  public Role getRoleByName(String role) {
    return roleRepository.findByName(role)
        .orElseThrow(() ->
            new RoleNotFoundException("Role: " + role + " not found")
        );
  }

  @Override
  public Role getRoleByShortName(String role) {
    return roleRepository.findByName("ROLE_" + role.toUpperCase())
        .orElseThrow(() ->
            new RoleNotFoundException("Role: " + role + " not found")
        );
  }

  @Override
  public Collection<Role> getAll() {
    return roleRepository.findAll();
  }
}
