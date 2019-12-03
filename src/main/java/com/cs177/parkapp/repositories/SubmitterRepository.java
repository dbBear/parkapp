package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {

  Optional<Submitter> findByUser(User user);
  Optional<Submitter> findByUserId_Email(String email);
  List<Submitter> findAllByUserId_EmailLike(String email);
  List<Submitter> findByIdIn(Collection<Long> ids);

}
