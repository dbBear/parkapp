package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

  private static final Long ID_1 = 1L;
  private static final String NAME_1 = "Name 1";
  private static final String DESCRIPTION_1 = "Description 1";

  private CategoryCommandToCategory converter;
  private CategoryCommand categoryCommand;

  @BeforeEach
  void setUp() {
    converter = new CategoryCommandToCategory();
//    categoryCommand = CategoryCommand.builder()
//        .id(ID_1)
//        .name(NAME_1)
//        .description(DESCRIPTION_1)
//        .build();
    categoryCommand = new CategoryCommand();
    categoryCommand.setId(ID_1);
    categoryCommand.setName(NAME_1);
    categoryCommand.setDescription(DESCRIPTION_1);
  }

  @Test
  void testNullParameter() {
    assertNull(converter.convert(null));
  }

  @Test
  void convert() {
    //given
    //when
    Category categoryReturned = converter.convert(categoryCommand);
    //then
    assertNotNull(categoryReturned);
    assertEquals(categoryCommand.getId(), categoryReturned.getId());
    assertEquals(categoryCommand.getName(), categoryReturned.getName());
    assertEquals(categoryCommand.getDescription(),
        categoryReturned.getDescription());
  }
}