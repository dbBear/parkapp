package com.cs177.parkapp.controllers;

import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.RangerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static com.cs177.parkapp.config.StaticNames.DEV_DIR;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RangerControllerTest {

  @Mock
  RangerService rangerService;
  @Mock
  ParkService parkService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders
            .standaloneSetup(new RangerController(rangerService, parkService))
            .build();
  }

  @Test
  void showRangers() throws Exception {
    //given
    when(rangerService.findAll()).thenReturn(new HashSet<>());
    //when
    //then
    mockMvc.perform(get("/rangers")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/rangers/rangerList"))
        .andExpect(model().attributeExists("rangers"));
  }
}