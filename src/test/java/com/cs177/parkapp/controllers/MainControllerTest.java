package com.cs177.parkapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.cs177.parkapp.controllers.StaticStuff.DEV_DIR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MainControllerTest {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(new MainController())
        .build();
  }

  @Test
  void getIndex() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(get("/")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/index"));
  }
}