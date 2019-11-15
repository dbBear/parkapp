package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.commands.CategoryCommand;
import com.cs177.parkapp.converters.CategoryCommandToCategory;
import com.cs177.parkapp.converters.CategoryToCategoryCommand;
import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.repositories.CategoryRepository;
import com.cs177.parkapp.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

  private static final Long ID_1 = 1L;
  private static final Long ID_2 = 2L;
  private static final String NAME_1 = "Name 1";
  private static final String NAME_2 = "Name 2";
  private static final String DESCRIPTION = "Description";

  @Mock
  CategoryRepository categoryRepository;
  @Mock
  CategoryToCategoryCommand categoryToCategoryCommand;
  @Mock
  CategoryCommandToCategory categoryCommandToCategory;
  private CategoryService categoryService;

  private Category category1;
  private Category category2;
  private List<Category> categories;
  private CategoryCommand categoryCommand1;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    categoryService = new CategoryServiceImpl(categoryRepository,
        categoryToCategoryCommand, categoryCommandToCategory);

    category1 = Category.builder()
        .id(ID_1)
        .name(NAME_1)
        .description(DESCRIPTION)
        .build();
    category2 = Category.builder()
        .id(ID_2)
        .name(NAME_2)
        .description(DESCRIPTION)
        .build();
    categories = new ArrayList<>(Arrays.asList(category1, category2));
    categoryCommand1 = CategoryCommand.builder()
        .id(ID_1)
        .name(NAME_1)
        .description(DESCRIPTION)
        .build();
  }

  @Test
  void getCategories() {
    //given
    when(categoryRepository.findAll()).thenReturn(categories);
    //when
    Set<Category> categoriesReturned = categoryService.getCategories();
    //then
    assertNotNull(categoriesReturned);
    assertEquals(2, categoriesReturned.size());
    verify(categoryRepository, times(1)).findAll();
    verifyNoMoreInteractions(categoryRepository);
  }

  @Test
  void findById() {
    //given
    when(categoryRepository.findById(anyLong()))
        .thenReturn(Optional.of(category1));
    //when
    Category categoryReturned = categoryService.findById(ID_1);
    //then
    assertNotNull(categoryReturned);
    assertEquals(ID_1, categoryReturned.getId());
    verify(categoryRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(categoryRepository);
  }

  @Test
  void findByName() {
    //given
    when(categoryRepository.findByName(anyString()))
        .thenReturn(Optional.of(category1));
    //when
    Category categoryReturned = categoryService.findByName(NAME_1);
    //then
    assertNotNull(categoryReturned);
    assertEquals(NAME_1, categoryReturned.getName());
    verify(categoryRepository, times(1)).findByName(anyString());
    verifyNoMoreInteractions(categoryRepository);
  }

  @Test
  void findCommandById() {
    //given
    when(categoryRepository.findById(anyLong()))
        .thenReturn(Optional.of(category1));
    when(categoryToCategoryCommand.convert(any())).thenReturn(categoryCommand1);
    //when
    CategoryCommand ccReturned = categoryService.findCommandById(ID_1);
    //then
    assertNotNull(ccReturned);
    assertEquals(categoryCommand1.getId(), ccReturned.getId());
    verify(categoryRepository, times(1)).findById(anyLong());
    verify(categoryToCategoryCommand, times(1)).convert(any());
    verifyNoMoreInteractions(categoryRepository);
    verifyNoMoreInteractions(categoryToCategoryCommand);
  }

  @Test
  void saveCategoryCommand() {
    //given
    when(categoryCommandToCategory.convert(any())).thenReturn(category1);
    when(categoryRepository.save(any())).thenReturn(category1);
    when(categoryToCategoryCommand.convert(any())).thenReturn(categoryCommand1);

    //when
    CategoryCommand ccReturned =
        categoryService.saveCategoryCommand(categoryCommand1);

    //then
    assertNotNull(ccReturned);
    assertEquals(categoryCommand1.getId(), ccReturned.getId());
    verify(categoryRepository, times(1)).save(any());
    verify(categoryCommandToCategory, times(1)).convert(any());
    verify(categoryToCategoryCommand, times(1)).convert(any());
    verifyNoMoreInteractions(categoryRepository);
    verifyNoMoreInteractions(categoryCommandToCategory);
  }
}