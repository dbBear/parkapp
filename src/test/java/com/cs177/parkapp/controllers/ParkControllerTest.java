package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.services.ParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static com.cs177.parkapp.config.StaticNames.DEV_DIR;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    when(parkService.findAll()).thenReturn(new HashSet<>());
    //when
    //then
    mockMvc.perform(get("/parks")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/parks/parkList"))
        .andExpect(model().attributeExists("parks"));

    verify(parkService, times(1)).findAll();
  }

  @Test
  void newPark() throws Exception{
    //given
    //when
    //then
    mockMvc.perform(get("/parks/new")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/parks/parkForm"))
        .andExpect(model().attributeExists("park"));
  }

  @Test
  void updatePark() throws Exception{
    //given
    when(parkService.findById(anyLong())).thenReturn(new Park());
    //when
    //then
    mockMvc.perform(get("/parks/update?id=1")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(DEV_DIR + "/parks/parkForm"))
        .andExpect(model().attributeExists("park"));
    verify(parkService, times(1)).findById(anyLong());
    verifyNoMoreInteractions(parkService);
  }

  @Test
  void saveOrUpdatePark() throws Exception{
    //given
    Park park = Park.builder()
        .id(1L)
        .name("park")
        .build();
    when(parkService.save(any(Park.class))).thenReturn(park);
    //when
    //then
    mockMvc.perform(post("/parks")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", park.getId().toString())
        .param("name", park.getName())
    ).andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/parks"));
    verify(parkService, times(1)).save(any(Park.class));
    verifyNoMoreInteractions(parkService);
  }

  @Test
  void deletePark() throws Exception {
    //given
    when(parkService.findById(anyLong())).thenReturn(new Park());
    //when
    //then
    mockMvc.perform(get("/parks/delete?id=1")).andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/parks"));
    verify(parkService, times(1)).findById(anyLong());
    verify(parkService, times(1)).delete(any(Park.class));
    verifyNoMoreInteractions(parkService);

  }
}