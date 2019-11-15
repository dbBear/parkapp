package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParkRepository extends JpaRepository<Park, Long> {

  List<Park> findAllByNameLike(String name);
  Optional<Park> findByName(String name);
}
