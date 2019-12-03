package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RangerRepository extends JpaRepository<Ranger, Long> {

  Optional<Ranger> findByUser(User user);
  List<Ranger> findByIdIn(Collection<Long> ids);
  Optional<Ranger> findByUserId_Email(String email);
  List<Ranger> findAllByUserId_EmailLike(String email);
}
