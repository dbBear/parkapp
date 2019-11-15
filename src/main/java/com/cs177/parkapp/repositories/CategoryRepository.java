package com.cs177.parkapp.repositories;

import com.cs177.parkapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByName(String name);
}
