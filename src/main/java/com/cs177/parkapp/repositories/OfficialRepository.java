package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Official;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OfficialRepository extends JpaRepository<Official, Long> {

  Optional<Official> findByEmail(String email);
  List<Official> findAllByLastNameIsLike(String name);
}
