package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static com.cs177.parkapp.controllers.StaticStuff.DEV_DIR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategoryControllerTest {

  @Mock
  CategoryService categoryService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders
            .standaloneSetup(new CategoryController(categoryService))
            .build();
  }

  @Test
  void listCategories() throws Exception {
    //given
    when(categoryService.getCategories()).thenReturn(new HashSet<>());
    //when
    //then
    mockMvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/categories/categoryList"))
        .andExpect(model().attributeExists("categories"));
  }

  @Test
  void newCategory() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(get("/categories/new"))
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/categories/categoryForm"))
        .andExpect(model().attributeExists("category"));
  }

  @Test
  void updateCategory() throws Exception {
    //given
    when(categoryService.findById(anyLong())).thenReturn(new Category());
    //when
    //then
    mockMvc.perform(get("/categories/update?id=1"))
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/categories/categoryForm"))
        .andExpect(model().attributeExists("category"));
  }

  @Test
  void saveCategory() throws Exception{
    //given
    Category category = Category.builder()
        .id(1L)
        .name("category")
        .description("category")
        .build();
    when(categoryService.save(any(Category.class))).thenReturn(category);
    //when
    //then
    mockMvc.perform(post("/categories")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", category.getId().toString())
        .param("name", category.getName())
        .param("description", category.getDescription())
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/categories"));
    verify(categoryService, times(1)).save(any(Category.class));
    verifyNoMoreInteractions(categoryService);
  }

  @Test
  void deleteCategory() throws Exception {
    //given
    when(categoryService.findById(anyLong())).thenReturn(new Category());
    //when
    //then
    mockMvc.perform(get("/categories/delete?id=1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/categories"));
    verify(categoryService, times(1)).findById(anyLong());
    verify(categoryService, times(1)).delete(any(Category.class));
    verifyNoMoreInteractions(categoryService);
  }
}