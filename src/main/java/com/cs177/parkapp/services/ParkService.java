package com.cs177.parkapp.services;

import com.cs177.parkapp.dto.ParkDto;
import com.cs177.parkapp.model.Park;

import java.util.Optional;
import java.util.Set;

public interface ParkService {
  boolean existsById(Long id);
  Set<Park> findAll();
  Park findById(Long id);
  Park findByName(String name);
  Set<Park> findByNameLike(String name);
  Park save(Park park);
  void delete(Park park);
}
