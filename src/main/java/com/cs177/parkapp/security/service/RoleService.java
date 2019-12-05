package com.cs177.parkapp.security.service;

import com.cs177.parkapp.security.entity.Role;

import java.util.Collection;

public interface RoleService {

  Role findById(Long id);
  Role findByName(String name);
  Role findByShortName(String name);
  Collection<Role> findAll();

}
