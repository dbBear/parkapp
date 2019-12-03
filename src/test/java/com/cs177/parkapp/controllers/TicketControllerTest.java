package com.cs177.parkapp.controllers;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.services.CategoryService;
import com.cs177.parkapp.services.ParkService;
import com.cs177.parkapp.services.SubmitterService;
import com.cs177.parkapp.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static com.cs177.parkapp.config.StaticStuff.DEV_DIR;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TicketControllerTest {

  @Mock
  TicketService ticketService;
  @Mock
  CategoryService categoryService;
  @Mock
  ParkService parkService;
  @Mock
  SubmitterService submitterService;
  @Mock
  AuthenticationFacade authenticationFacade;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders
            .standaloneSetup(
                new TicketController(
                    ticketService,
                    categoryService,
                    parkService,
                    submitterService,
                    authenticationFacade
                ))
            .build();
  }

  @Test
  void showTickets() throws Exception {
    //given
    when(ticketService.getTickets()).thenReturn(new HashSet<Ticket>());
    //when
    //then
    mockMvc.perform(get("/tickets")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("tickets"))
        .andExpect(view().name(DEV_DIR + "/tickets/ticketList"));
  }

  @Test
  void newTicket() throws Exception {
    //given
    when(categoryService.getCategories()).thenReturn(new HashSet<>());
    when(parkService.getParks()).thenReturn(new HashSet<>());
    //when
    //then
    mockMvc.perform(get("/tickets/new")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("ticket"))
        .andExpect(model().attributeExists("categories"))
        .andExpect(model().attributeExists("parks"))
        .andExpect(view().name(DEV_DIR + "/tickets/ticketForm"));
    ;
    verify(categoryService, times(1)).getCategories();
    verify(parkService, times(1)).getParks();
    verifyNoMoreInteractions(categoryService);
    verifyNoMoreInteractions(parkService);
  }

  @Test
  void saveTicket() throws Exception {
    //given
    Ticket ticket = Ticket.builder()
        .id(1L)
        .name("ticket")
        .category(new Category())
        .submitter(new Submitter())
        .park(new Park())
        .build();
    when(submitterService.findByEmail(anyString())).thenReturn(null);
    when(ticketService.save(any(Ticket.class))).thenReturn(ticket);
    //when
    //then
    mockMvc.perform(post("/tickets/new")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("name", ticket.getName())
        .param("category", "")
        .param("submitter", "")
        .param("park", "")
    ).andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/tickets"));
    verify(submitterService, times(1)).findByEmail(anyString());
    verify(ticketService, times(1)).save(any(Ticket.class));
    verifyNoMoreInteractions(submitterService);
    verifyNoMoreInteractions(ticketService);
  }
}