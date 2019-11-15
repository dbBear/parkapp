package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

  private static final Long ID_1 = 1L;
  private static final String NAME_1 = "Name 1";
  private static final String DESCRIPTION_1 = "Description 1";

  private CategoryToCategoryCommand converter;
  private Category category;

  @BeforeEach
  void setUp() {
    converter = new CategoryToCategoryCommand();
    category = Category.builder()
        .id(ID_1)
        .name(NAME_1)
        .description(DESCRIPTION_1)
        .build();
  }

  @Test
  void testNullParameter() {
    assertNull(converter.convert(null));
  }

  @Test
  void convert() {
    //given
    //when
    CategoryCommand ccReturned = converter.convert(category);
    //then
    assertNotNull(ccReturned);
    assertEquals(category.getId(), ccReturned.getId());
    assertEquals(category.getName(), ccReturned.getName());
    assertEquals(category.getDescription(), ccReturned.getDescription());
  }
}