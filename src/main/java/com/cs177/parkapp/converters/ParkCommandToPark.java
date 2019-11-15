package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.ParkCommand;
import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.model.Park;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ParkCommandToPark
  implements Converter<ParkCommand, Park>
{
  private final OfficialCommandToOfficial officialCommandToOfficial;
  private final TicketCommandToTicket ticketCommandToTicket;

  public ParkCommandToPark(
      @Lazy OfficialCommandToOfficial officialCommandToOfficial,
      @Lazy TicketCommandToTicket ticketCommandToTicket)
  {
    this.officialCommandToOfficial = officialCommandToOfficial;
    this.ticketCommandToTicket = ticketCommandToTicket;
  }

  @Synchronized
  @Nullable
  @Override
  public Park convert(ParkCommand parkCommand) {
    if(parkCommand == null) {
      return null;
    }

    Park park = Park.builder()
        .id(parkCommand.getId())
        .name(parkCommand.getName())
        .build();

    if(parkCommand.getOfficials() != null && parkCommand.getOfficials().size() > 0) {
      parkCommand.getOfficials().forEach(
          officialCommand ->
              park.getOfficials()
                  .add(officialCommandToOfficial.convert(officialCommand))
      );
    }

    if(parkCommand.getTickets() != null && parkCommand.getTickets().size() > 0) {
      parkCommand.getTickets().forEach(
          ticketCommand ->
              park.getTickets()
                  .add(ticketCommandToTicket.convert(ticketCommand))
      );
    }
    return park;
  }
}
