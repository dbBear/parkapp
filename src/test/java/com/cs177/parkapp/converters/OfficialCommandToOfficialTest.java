package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.OfficialCommand;
import com.cs177.parkapp.model.Official;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class OfficialCommandToOfficialTest {

  private static final Long ID_1 = 1L;
  private static final String FIRST_NAME = "First Name";
  private static final String LAST_NAME = "Last Name";
  private static final String EMAIL = "email";

  @Mock
  private ParkCommandToPark parkConverter;
  private OfficialCommandToOfficial officialConverter;
  private OfficialCommand officialCommand;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    officialConverter = new OfficialCommandToOfficial(parkConverter);
    officialCommand = OfficialCommand.builder()
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
    Official officialReturned = officialConverter.convert(officialCommand);
    //then
    assertNotNull(officialReturned);
    assertEquals(officialCommand.getId(), officialReturned.getId());
    assertEquals(officialCommand.getFirstName(), officialReturned.getFirstName());
    assertEquals(officialCommand.getLastName(), officialReturned.getLastName());
    assertEquals(officialCommand.getEmail(), officialReturned.getEmail());
  }
}