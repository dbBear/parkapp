package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RangerRepository extends JpaRepository<Ranger, Long> {

  Optional<Ranger> findByUser(User user);
  Optional<Ranger> findByUserEmail(String email);
  List<Ranger> findAllByUserEmailLike(String email);
  List<Ranger> findAllByPark(Park park);
}
