package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.exceptions.NameNotFoundException;
import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.repositories.CategoryRepository;
import com.cs177.parkapp.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Set<Category> getCategories() {
    return new HashSet<Category>(categoryRepository.findAll());
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("Category id: " + id + " not found")
        );
  }

  @Override
  public Category findByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() ->
            new NameNotFoundException("Category name:" + name + " not found")
        );
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Category category) {
    categoryRepository.delete(category);
  }
}
