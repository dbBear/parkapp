package com.cs177.parkapp.security.service;

import com.cs177.parkapp.security.entity.Role;

import java.util.Collection;

public interface RoleService {

  Role getRoleByName(String name);
  Role getRoleByShortName(String name);
  Collection<Role> getAll();

}
