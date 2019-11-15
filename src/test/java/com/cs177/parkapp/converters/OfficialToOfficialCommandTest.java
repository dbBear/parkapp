package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.OfficialCommand;
import com.cs177.parkapp.model.Official;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class OfficialToOfficialCommandTest {

  private static final Long ID_1 = 1L;
  private static final String FIRST_NAME = "First Name";
  private static final String LAST_NAME = "Last Name";
  private static final String EMAIL = "email";

  @Mock
  private ParkToParkCommand parkConverter;
  private OfficialToOfficialCommand officialConverter;
  private Official official;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    officialConverter = new OfficialToOfficialCommand(parkConverter);
    official = Official.builder()
        .id(ID_1)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .email(EMAIL)
        .build();
  }

  @Test
  void testNullParameter() {
    assertNull(officialConverter.convert(null));
  }

  @Test
  void convert() {
    //given
    //when
    OfficialCommand ocReturned = officialConverter.convert(official);
    //then
    assertNotNull(ocReturned);
    assertEquals(official.getId(), ocReturned.getId());
    assertEquals(official.getFirstName(), ocReturned.getFirstName());
    assertEquals(official.getLastName(), ocReturned.getLastName());
    assertEquals(official.getEmail(), ocReturned.getEmail());
  }
}