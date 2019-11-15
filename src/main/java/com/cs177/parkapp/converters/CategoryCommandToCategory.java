package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory
  implements Converter<CategoryCommand, Category>
{

  @Override
  public Category convert(CategoryCommand categoryCommand) {
    if (categoryCommand == null) {
      return null;
    }
    return Category.builder()
        .id(categoryCommand.getId())
        .name(categoryCommand.getName())
        .description(categoryCommand.getDescription())
        .build();
  }
}
