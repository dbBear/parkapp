package com.cs177.parkapp.services;

import com.cs177.parkapp.model.Category;

import java.util.Set;

public interface CategoryService {
  Set<Category> getCategories();
  Category findById(Long id);
  Category findByName(String name);
  Category save(Category category);
  void delete(Category category);
}

