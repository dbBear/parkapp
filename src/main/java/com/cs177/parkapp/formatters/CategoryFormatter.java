package com.cs177.parkapp.formatters;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@AllArgsConstructor
@Component
public class CategoryFormatter implements Formatter<Category> {

  private final CategoryService categoryService;

  @Override
  public Category parse(String s, Locale locale) throws ParseException {
    return categoryService.findById(Long.parseLong(s));

  }

  @Override
  public String print(Category category, Locale locale) {
    return category.getName();
  }
}
