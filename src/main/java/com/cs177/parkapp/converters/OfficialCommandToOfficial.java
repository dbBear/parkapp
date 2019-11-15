package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.OfficialCommand;
import com.cs177.parkapp.model.Official;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OfficialCommandToOfficial
  implements Converter<OfficialCommand, Official>
{
  private final ParkCommandToPark parkCommandToPark;

  public OfficialCommandToOfficial(@Lazy ParkCommandToPark parkCommandToPark) {
    this.parkCommandToPark = parkCommandToPark;
  }

  @Synchronized
  @Nullable
  @Override
  public Official convert(OfficialCommand officialCommand) {
    if(officialCommand == null) {
      return null;
    }
    return Official.builder()
        .id(officialCommand.getId())
        .firstName(officialCommand.getFirstName())
        .lastName(officialCommand.getLastName())
        .email(officialCommand.getEmail())
        .park(parkCommandToPark.convert(officialCommand.getPark()))
        .build();
  }
}
