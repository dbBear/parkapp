package com.cs177.parkapp.services;

import com.cs177.parkapp.model.Ranger;

import java.util.Set;

public interface RangerService {
  Set<Ranger> findAll();
  Ranger findById(Long id);
  Ranger findByEmail(String email);
  Set<Ranger> findByEmailLike(String email);
  Ranger save(Ranger ranger);
  void delete(Ranger ranger);
}
