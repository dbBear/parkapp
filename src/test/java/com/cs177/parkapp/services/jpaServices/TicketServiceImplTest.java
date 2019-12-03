package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.services.SubmitterService;
import com.cs177.parkapp.security.facade.AuthenticationFacade;
import com.cs177.parkapp.services.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

  private static final Long ID_1 = 1L;
  private static final Long ID_2 = 2L;
  private static final String NAME_1 = "Ticket 1";
  private static final String NAME_2 = "Ticket 2";
  private static final String DESCRIPTION = "description";

  @Mock Category category;
  @Mock Submitter submitter;
  @Mock Park park;
  @Mock
  TicketRepository ticketRepository;
  @Mock
  SubmitterService submitterService;
  AuthenticationFacade authenticationFacade;
  private TicketServiceImpl ticketService;

  private Ticket ticket1;
  private Ticket ticket2;
  private List<Ticket> tickets;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    ticketService = new TicketServiceImpl(ticketRepository,
        submitterService, authenticationFacade);
    ticket1 = Ticket.builder()
        .id(ID_1)
        .category(category)
        .name(NAME_1)
        .description(DESCRIPTION)
        .submitter(submitter)
        .park(park)
        .build();
    ticket2 = Ticket.builder()
        .id(ID_2)
        .category(category)
        .name(NAME_2)
        .description(DESCRIPTION)
        .submitter(submitter)
        .park(park)
        .build();
    tickets = new ArrayList<>(Arrays.asList(ticket1, ticket2));
  }

  @Test
  void getTickets() {
    //given
    //when
    when(ticketRepository.findAll()).thenReturn(tickets);
    Set<Ticket> ticketsReturned = ticketService.getTickets();
    //then
    assertNotNull(ticketsReturned);
    assertEquals(tickets.size(), ticketsReturned.size());
    for(Ticket ticket : tickets) {
      assertThat(ticketsReturned, hasItem(ticket));
    }
    verify(ticketRepository, times(1)).findAll();
    verifyNoMoreInteractions(ticketRepository);

  }

  @Test
  void findBydId() {
    //given
    when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket1));
    //when
    Ticket ticketReturned = ticketService.findBydId(ID_1);
    //then
    assertNotNull(ticketReturned);
    assertEquals(ticket1, ticketReturned);
    verify(ticketRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void save() {
    //given
    when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket1);
    //when
    Ticket ticketSaved = ticketService.save(ticket1);
    //then
    assertNotNull(ticketSaved);
    assertEquals(ticket1, ticketSaved);
    verify(ticketRepository, times(1)).save(any());
    verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void delete() {
    //given
    //when
    ticketService.delete(ticket1);
    //then
    verify(ticketRepository, times(1)).delete(any(Ticket.class));
    verifyNoMoreInteractions(ticketRepository);
  }
}