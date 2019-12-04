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
  Optional<Submitter> findByUserEmail(String email);
//  List<Submitter> findByUserEmail(String email);
  List<Submitter> findAllByUserEmailLike(String email);
  List<Submitter> findByIdIn(Collection<Long> ids);

}
