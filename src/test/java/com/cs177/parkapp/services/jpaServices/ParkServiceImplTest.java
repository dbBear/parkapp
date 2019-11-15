package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.repositories.ParkRepository;
import com.cs177.parkapp.services.jpaServices.ParkServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ParkServiceImplTest {

  private static final Long ID_1 = 1L;
  private static final Long ID_2 = 2L;
  private static final String NAME = "Park Name";

  @Mock
  ParkRepository parkRepository;
  private ParkServiceImpl parkService;

  private Park park1;
  private Park park2;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    parkService = new ParkServiceImpl(parkRepository);
    park1 = Park.builder()
        .id(ID_1)
        .name(NAME)
        .build();
    park2 = Park.builder()
        .id(ID_2)
        .name(NAME)
        .build();
  }

  @Test
  void getParks() {
    //given
    List<Park> parks = new ArrayList<>(Arrays.asList(park1, park2));

    //when
    when(parkRepository.findAll()).thenReturn(parks);
    Set<Park> parksReturned = parkService.getParks();

    //then
    assertNotNull(parksReturned);
    assertEquals(2, parksReturned.size());
    verify(parkRepository, times(1)).findAll();
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findById() {
    //given
    //when
    when(parkRepository.findById(anyLong())).thenReturn(Optional.of(park1));
    Park returnPark = parkService.findById(ID_1);

    //then
    assertNotNull(returnPark);
    verify(parkRepository,times(1)).findById(anyLong());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByIdNotFound() {
    //give
    //when
    when(parkRepository.findById(anyLong())).thenReturn(Optional.empty());
    //then
    Assertions.assertThrows(EntityNotFoundException.class, () -> {
      Park returnPark = parkService.findById(ID_1);
    });
    verify(parkRepository,times(1)).findById(anyLong());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByName() {
    //given
    //when
    when(parkRepository.findByName(anyString())).thenReturn(Optional.of(park1));
    Park parkReturned = parkService.findByName(park1.getName());
    //then
    assertNotNull(parkReturned);
    verify(parkRepository,times(1)).findByName(anyString());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByNameNotFound() {
    //given
    //when
    when(parkRepository.findByName(anyString())).thenReturn(Optional.empty());
    //then
    Assertions.assertThrows(EntityNotFoundException.class, () -> {
      Park returnPark = parkService.findByName("");
    });
  }

  @Test
  void findByNameLike() {
    //give
    List<Park> parks = new ArrayList<>(Arrays.asList(park1, park2));
    //when
    when(parkRepository.findAllByNameLike(anyString())).thenReturn(parks);
    Set<Park> parksReturned = parkService.findByNameLike("");
    //then
    assertNotNull(parksReturned);
    assertEquals(parksReturned.size(), 2);
    verify(parkRepository, times(1)).findAllByNameLike(anyString());
    verifyNoMoreInteractions(parkRepository);
  }
}