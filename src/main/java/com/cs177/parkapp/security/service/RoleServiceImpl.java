package com.cs177.parkapp.security.service;

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
  public Role getRoleByName(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public Role getRoleByShortName(String name) {
    return roleRepository.findByName("ROLE_" + name.toUpperCase());
  }

  @Override
  public Collection<Role> getAll() {
    return roleRepository.findAll();
  }
}
