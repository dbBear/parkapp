package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.services.ParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ParkControllerTest {

  @Mock
  ParkService parkService;
  private ParkController parkController;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    parkController = new ParkController(parkService);
    mockMvc = MockMvcBuilders.standaloneSetup(parkController)
//        .setControllerAdvice(new ControllerExceptionHandler())
        .build();
  }

  @Test
  void showParks() throws Exception{
    //given
    Park park1 = Park.builder()
        .id(2L)
        .name("park1")
        .build();
    Park park2 = Park.builder()
        .id(2L)
        .name("park2")
        .build();
    Set<Park> parks = new HashSet<>(Arrays.asList(park1, park2));

    //when
    when(parkService.getParks()).thenReturn(parks);

    //then
    mockMvc.perform(get("/park"))
        .andExpect(status().isOk())
        .andExpect(view().name("parks/list"))
        .andExpect(model().attributeExists("parks"));

    verify(parkService, times(1)).getParks();
  }
}