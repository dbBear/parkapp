package com.cs177.parkapp.services;

import com.cs177.parkapp.model.Ranger;

import java.util.Set;

public interface RangerService {
  Set<Ranger> getRangers();
  Ranger findById(Long id);
  Ranger findByEmail(String email);
  Ranger saveRanger(Ranger ranger);
}
