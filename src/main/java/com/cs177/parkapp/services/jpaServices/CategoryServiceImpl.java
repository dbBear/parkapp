package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.repositories.CategoryRepository;
import com.cs177.parkapp.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(
      CategoryRepository categoryRepository)
  {
    this.categoryRepository = categoryRepository;
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

  @Override
  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }
}
