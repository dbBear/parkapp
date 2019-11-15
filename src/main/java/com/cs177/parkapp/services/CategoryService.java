package com.cs177.parkapp.services;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.model.Category;

import java.util.Set;

public interface CategoryService {

  Set<Category> getCategories();
  Category findById(Long id);
  Category findByName(String name);

  CategoryCommand findCommandById(Long id);
  CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);
}

