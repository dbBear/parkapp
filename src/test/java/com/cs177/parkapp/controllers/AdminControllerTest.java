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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminControllerTest {

  @Mock
  CategoryService categoryService;
  @Mock
  ParkService parkService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders
            .standaloneSetup(new AdminController(categoryService, parkService))
            .build();
  }

  @Test
  void listCategories() throws Exception {
    //given
    when(categoryService.getCategories()).thenReturn(new HashSet<Category>());
    //when
    //then
    mockMvc.perform(get("/admin/categories"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/categories/list"))
        .andExpect(model().attributeExists("categories"));
  }

  @Test
  void newCategory() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(get("/admin/categories/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/categories/categoryForm"))
        .andExpect(model().attributeExists("category"));
  }

}