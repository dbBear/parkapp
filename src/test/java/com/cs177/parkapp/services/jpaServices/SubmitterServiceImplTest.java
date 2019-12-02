package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.repositories.SubmitterRepository;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.repository.UserRepository;
import com.cs177.parkapp.security.service.UserService;
import com.cs177.parkapp.services.SubmitterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.MapKeyClass;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


class SubmitterServiceImplTest {

  private static final Long ID_1 = 1L;
  private static final Long ID_2 = 2L;
  private static final String F_NAME_1 = "First Name 1";
  private static final String F_NAME_2 = "First Name 2";
  private static final String L_NAME_1 = "Last Name 1";
  private static final String L_NAME_2 = "Last Name 2";
  private static final String EMAIL_1 = "Email 1";
  private static final String EMAIL_2 = "Email 2";

  @Mock
  SubmitterRepository submitterRepository;
  @Mock
  UserRepository userRepository;

  private SubmitterService submitterService;

  private User user1;
  private User user2;
  private Submitter submitter1;
  private Submitter submitter2;
  private List<Submitter> submitters;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    submitterService = new SubmitterServiceImpl(submitterRepository, userRepository);
    user1 = User.builder()
        .firstName(F_NAME_1)
        .lastName(L_NAME_1)
        .email(EMAIL_1)
        .build();

    user2 = User.builder()
        .firstName(F_NAME_2)
        .lastName(L_NAME_2)
        .email(EMAIL_2)
        .build();


    submitter1 = Submitter.builder()
        .id(ID_1)
        .user(user1)
        .build();
    submitter2 = Submitter.builder()
        .id(ID_2)
        .user(user2)
        .build();
    submitters = new ArrayList<>(Arrays.asList(submitter1, submitter2));
  }

  @Test
  void getSubmitters() {
    //given
    when(submitterRepository.findAll()).thenReturn(submitters);
    //when
    Set<Submitter> submittersReturned = submitterService.getSubmitters();
    //then
    assertNotNull(submittersReturned);
    assertEquals(2, submittersReturned.size());
    assertThat(submittersReturned, hasItem(submitter1));
    assertThat(submittersReturned, hasItem(submitter2));
    verify(submitterRepository, times(1)).findAll();
    verifyNoMoreInteractions(submitterRepository);
  }

  @Test
  void findById() {
    //given
    when(submitterRepository.findById(anyLong())).thenReturn(Optional.of(submitter1));
    //when
    Submitter submitterReturned = submitterService.findById(submitter1.getId());
    //then
    assertNotNull(submitterReturned);
    assertEquals(submitter1, submitterReturned);
    verify(submitterRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(submitterRepository);
  }


  @Disabled
  @Test
  void findByEmail() {
    //given
    when(submitterRepository.findByUser(any(User.class))).thenReturn(Optional.of(submitter1));
    //when
    Submitter submitterReturned =
        submitterService.findByEmail(submitter1.getUser().getEmail());
    //then
    assertNotNull(submitterReturned);
    assertEquals(submitter1, submitterReturned);
    verify(submitterRepository, times(1)).findByUser(any(User.class));
    verifyNoMoreInteractions(submitterRepository);
  }

  @Test
  void findByEmailLike() {
    //given
    when(submitterRepository.findByIdIn(any())).thenReturn(submitters);
    //when
    Set<Submitter> submittersReturned = submitterService.findByEmailLike("");
    //then
    assertNotNull(submitterRepository);
    assertEquals(submitters.size(), submittersReturned.size());
    for(Submitter submitter : submitters) {
      assertThat(submittersReturned, hasItem(submitter));
    }
    verify(submitterRepository, times(1)).findByIdIn(any());
    verifyNoMoreInteractions(submitterRepository);
  }

  @Test
  void save() {
    //given
    when(submitterRepository.save(any(Submitter.class))).thenReturn(submitter1);
    //when
    Submitter submitterSaved = submitterService.save(submitter1);
    //then
    assertNotNull(submitterSaved);
    assertEquals(submitter1, submitterSaved);
    verify(submitterRepository, times(1)).save(any());
    verifyNoMoreInteractions(submitterRepository);
  }

  @Test
  void delete() {
    //given
    //when
    submitterService.delete(submitter1);
    //then
    verify(submitterRepository, times(1)).delete(any(Submitter.class));
    verifyNoMoreInteractions(submitterRepository);
  }
}