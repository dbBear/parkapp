package com.cs177.parkapp.security.service;

import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.exceptions.NameNotFoundException;
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
  public Role findById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("Role id:" + id + " not found")
        );
  }

  @Override
  public Role findByName(String role) {
    return roleRepository.findByName(role)
        .orElseThrow(() ->
            new NameNotFoundException("Role name: " + role + " not found")
        );
  }

  @Override
  public Role findByShortName(String role) {
    return roleRepository.findByName("ROLE_" + role.toUpperCase())
        .orElseThrow(() ->
            new NameNotFoundException("Role name: " + role + " not found")
        );
  }

  @Override
  public Collection<Role> findAll() {
    return roleRepository.findAll();
  }
}
