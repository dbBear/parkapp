package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.ParkCommand;
import com.cs177.parkapp.model.Park;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ParkToParkCommand
  implements Converter<Park, ParkCommand>
{
  private final OfficialToOfficialCommand officialToOfficialCommand;
  private final TicketToTicketCommand ticketToTicketCommand;

  public ParkToParkCommand(
      @Lazy OfficialToOfficialCommand officialToOfficialCommand,
      @Lazy TicketToTicketCommand ticketToTicketCommand)
  {
    this.officialToOfficialCommand = officialToOfficialCommand;
    this.ticketToTicketCommand = ticketToTicketCommand;
  }

  @Synchronized
  @Nullable
  @Override
  public ParkCommand convert(Park park) {
    if(park == null) {
      return null;
    }

    ParkCommand parkCommand = ParkCommand.builder()
        .id(park.getId())
        .name(park.getName())
        .build();

    if(park.getOfficials() != null && park.getOfficials().size() > 0) {
      park.getOfficials().forEach(
          official ->
              parkCommand.getOfficials()
                  .add(officialToOfficialCommand.convert(official))
      );
    }

    if(park.getTickets() != null && park.getTickets().size() > 0) {
      park.getTickets().forEach(
          ticket ->
              parkCommand.getTickets()
                  .add(ticketToTicketCommand.convert(ticket))

      );
    }
    return parkCommand;
  }
}
