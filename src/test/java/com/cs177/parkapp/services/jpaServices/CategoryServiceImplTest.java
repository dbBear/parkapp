package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.repositories.CategoryRepository;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
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
  private CategoryService categoryService;

  private Category category1;
  private Category category2;
  private List<Category> categories;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    categoryService = new CategoryServiceImpl(categoryRepository);

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
    for(Category category: categories) {
      assertThat(categoriesReturned, hasItem(category));
    }
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
    assertEquals(category1, categoryReturned);
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
    assertEquals(category1, categoryReturned);
    verify(categoryRepository, times(1)).findByName(anyString());
    verifyNoMoreInteractions(categoryRepository);
  }

  @Test
  void save() {
    //given
    when(categoryRepository.save(any(Category.class))).thenReturn(category1);
    //when
    Category categorySaved = categoryService.save(category1);
    //then
    assertNotNull(categorySaved);
    assertEquals(category1, categorySaved);
    verify(categoryRepository, times(1)).save(any());
    verifyNoMoreInteractions(categoryRepository);
  }

  @Test
  void delete() {
    //given
    //when
    categoryService.delete(category1);
    //then
    verify(categoryRepository, times(1)).delete(any());
    verifyNoMoreInteractions(categoryRepository);
  }
}