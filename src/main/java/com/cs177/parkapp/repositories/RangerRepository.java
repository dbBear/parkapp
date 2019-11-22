package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Ranger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RangerRepository extends JpaRepository<Ranger, Long> {

  Optional<Ranger> findByEmail(String email);
  List<Ranger> findAllByLastNameIsLike(String name);
}
