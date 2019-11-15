package com.cs177.parkapp.converters;

import com.cs177.parkapp.commands.OfficialCommand;
import com.cs177.parkapp.model.Official;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OfficialToOfficialCommand
    implements Converter<Official, OfficialCommand>
{

  public final ParkToParkCommand parkToParkCommand;

  public OfficialToOfficialCommand(@Lazy ParkToParkCommand parkToParkCommand) {
    this.parkToParkCommand = parkToParkCommand;
  }

  @Synchronized
  @Nullable
  @Override
  public OfficialCommand convert(Official official) {
    if(official == null) {
      return null;
    }
    return OfficialCommand.builder()
        .id(official.getId())
        .firstName(official.getFirstName())
        .lastName(official.getLastName())
        .email(official.getEmail())
        .park(parkToParkCommand.convert(official.getPark()))
        .build();
  }
}
