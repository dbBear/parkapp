package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand
  implements Converter<Category, CategoryCommand>
{
  @Synchronized
  @Nullable
  @Override
  public CategoryCommand convert(Category category) {
    if (category == null) {
      return null;
    }
//    return CategoryCommand.builder()
//        .id(category.getId())
//        .name(category.getName())
//        .description(category.getDescription())
//        .build();
    final CategoryCommand categoryCommand = new CategoryCommand();
    categoryCommand.setId(category.getId());
    categoryCommand.setName(category.getName());
    categoryCommand.setDescription(category.getDescription());
    return categoryCommand;
  }
}
