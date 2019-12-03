package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.repositories.ParkRepository;
import com.cs177.parkapp.services.ParkServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ParkServiceImplTest {

  private static final Long ID_1 = 1L;
  private static final Long ID_2 = 2L;
  private static final String NAME_1 = "Park Name 1";
  private static final String NAME_2 = "Park Name 2";

  @Mock
  ParkRepository parkRepository;
  private ParkServiceImpl parkService;

  private Park park1;
  private Park park2;
  List<Park> parks;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    parkService = new ParkServiceImpl(parkRepository);
    park1 = Park.builder()
        .id(ID_1)
        .name(NAME_1)
        .build();
    park2 = Park.builder()
        .id(ID_2)
        .name(NAME_2)
        .build();
    parks = new ArrayList<>(Arrays.asList(park1, park2));
  }

  @Test
  void getParks() {
    //given
    when(parkRepository.findAll()).thenReturn(parks);
    //when
    Set<Park> parksReturned = parkService.getParks();
    //then
    assertNotNull(parksReturned);
    assertEquals(parks.size(), parksReturned.size());
    for(Park park: parks) {
      assertThat(parksReturned, hasItem(park));
    }
    verify(parkRepository, times(1)).findAll();
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findById() {
    //given
    when(parkRepository.findById(anyLong())).thenReturn(Optional.of(park1));
    //when
    Park parkReturned = parkService.findById(ID_1);
    //then
    assertNotNull(parkReturned);
    assertEquals(park1, parkReturned);
    verify(parkRepository,times(1)).findById(anyLong());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByIdNotFound() {
    //give
    when(parkRepository.findById(anyLong())).thenReturn(Optional.empty());
    //when
    //then
    Assertions.assertThrows(EmailNotFoundException.class, () -> {
      Park returnPark = parkService.findById(ID_1);
    });
    verify(parkRepository,times(1)).findById(anyLong());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByName() {
    //given
    when(parkRepository.findByName(anyString())).thenReturn(Optional.of(park1));
    //when
    Park parkReturned = parkService.findByName(park1.getName());
    //then
    assertNotNull(parkReturned);
    assertEquals(park1, parkReturned);
    verify(parkRepository,times(1)).findByName(anyString());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void findByNameNotFound() {
    //given
    when(parkRepository.findByName(anyString())).thenReturn(Optional.empty());
    //when
    //then
    Assertions.assertThrows(EmailNotFoundException.class, () -> {
      Park returnPark = parkService.findByName("");
    });
  }

  @Test
  void findByNameLike() {
    //give
    when(parkRepository.findAllByNameLike(anyString())).thenReturn(parks);
    //when
    Set<Park> parksReturned = parkService.findByNameLike("Park");
    //then
    assertNotNull(parksReturned);
    assertEquals(parks.size(), parksReturned.size());
    for(Park park : parks) {
      assertThat(parksReturned, hasItem(park));
    }
    verify(parkRepository, times(1)).findAllByNameLike(anyString());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void save() {
    //given
    when(parkRepository.save(any(Park.class))).thenReturn(park1);
    //when
    Park parkSaved = parkService.save(park1);
    //then
    assertNotNull(parkSaved);
    assertEquals(park1, parkSaved);
    verify(parkRepository, times(1)).save(any());
    verifyNoMoreInteractions(parkRepository);
  }

  @Test
  void delete() {
    //given
    //when
    parkService.delete(park1);
    //then
    verify(parkRepository, times(1)).delete(any());
    verifyNoMoreInteractions(parkRepository);
  }
}