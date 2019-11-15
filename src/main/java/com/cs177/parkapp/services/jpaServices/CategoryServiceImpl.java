package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.converters.CategoryCommandToCategory;
import com.cs177.parkapp.converters.CategoryToCategoryCommand;
import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.repositories.CategoryRepository;
import com.cs177.parkapp.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryToCategoryCommand categoryToCategoryCommand;
  private final CategoryCommandToCategory categoryCommandToCategory;

  public CategoryServiceImpl(
      CategoryRepository categoryRepository,
      CategoryToCategoryCommand categoryToCategoryCommand,
      CategoryCommandToCategory categoryCommandToCategory)
  {
    this.categoryRepository = categoryRepository;
    this.categoryToCategoryCommand = categoryToCategoryCommand;
    this.categoryCommandToCategory = categoryCommandToCategory;
  }

  @Override
  public Set<Category> getCategories() {
    return new HashSet<Category>(categoryRepository.findAll());
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException("Category id:" + id + " not found")
        );
  }

  @Override
  public Category findByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() ->
            new EntityNotFoundException("Category name:" + name + " not found")
        );
  }

  @Transactional
  @Override
  public CategoryCommand findCommandById(Long id) {
    return categoryToCategoryCommand.convert(this.findById(id));
  }

  @Override
  public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand) {
    Category detachedCategory =
        categoryCommandToCategory.convert(categoryCommand);
    Category savedCategory = categoryRepository.save(detachedCategory);
    return categoryToCategoryCommand.convert(savedCategory);
  }
}
