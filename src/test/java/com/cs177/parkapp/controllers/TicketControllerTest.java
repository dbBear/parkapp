package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TicketControllerTest {

  @Mock
  TicketService ticketService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders
            .standaloneSetup(new TicketController(ticketService))
            .build();
  }

  @Test
  void showTickets() throws Exception {
    //given
    when(ticketService.getTickets()).thenReturn(new HashSet<Ticket>());
    //when
    //then
    mockMvc.perform(get("/ticket"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("tickets"))
        .andExpect(view().name("tickets/list"));
  }
}