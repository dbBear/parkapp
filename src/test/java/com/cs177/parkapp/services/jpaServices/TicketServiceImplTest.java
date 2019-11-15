package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.model.Category;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.model.Ticket;
import com.cs177.parkapp.repositories.TicketRepository;
import com.cs177.parkapp.services.jpaServices.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

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
  private TicketServiceImpl ticketService;

  Ticket ticket1;
  Ticket ticket2;
  List<Ticket> tickets;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    ticketService = new TicketServiceImpl(ticketRepository);

    ticket1 = Ticket.builder()
        .id(ID_1)
        .category(category)
        .name(NAME_1)
        .date(new Date())
        .description(DESCRIPTION)
        .submitter(submitter)
        .park(park)
        .build();
    ticket2 = Ticket.builder()
        .id(ID_2)
        .category(category)
        .name(NAME_2)
        .date(new Date())
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
    assertEquals(2, ticketsReturned.size());
    verify(ticketRepository, times(1)).findAll();
    verifyNoMoreInteractions(ticketRepository);

  }

  @Test
  void findBydId() {
    //given
    //when
    when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket1));
    Ticket ticketReturned = ticketService.findBydId(ID_1);
    //then
    assertNotNull(ticketReturned);
    assertEquals(ID_1, ticketReturned.getId());
    verify(ticketRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(ticketRepository);
  }
}