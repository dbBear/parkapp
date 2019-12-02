package com.cs177.parkapp.security.repository;

import com.cs177.parkapp.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  List<User> findAllByEmailLike(String email);

}
