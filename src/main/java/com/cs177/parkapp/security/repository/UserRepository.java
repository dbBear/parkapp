package com.cs177.parkapp.security.repository;

import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  List<User> findAllByEmailLike(String email);
  Optional<User> findBySubmitter(Submitter submitter);
  Optional<User> findByRanger(Ranger ranger);

}
