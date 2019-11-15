package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.ParkCommand;
import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.model.Ticket;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TicketCommandToTicket
    implements Converter<TicketCommand, Ticket>
{
  private final CategoryCommandToCategory categoryCommandToCategory;
  private final SubmitterCommandToSubmitter submitterCommandToSubmitter;
  private final ParkCommandToPark parkCommandToPark;

  public TicketCommandToTicket(
      @Lazy CategoryCommandToCategory categoryCommandToCategory,
      @Lazy SubmitterCommandToSubmitter submitterCommandToSubmitter,
      @Lazy ParkCommandToPark parkCommandToPark)
  {
    this.categoryCommandToCategory = categoryCommandToCategory;
    this.submitterCommandToSubmitter = submitterCommandToSubmitter;
    this.parkCommandToPark = parkCommandToPark;
  }

  @Synchronized
  @Nullable
  @Override
  public Ticket convert(TicketCommand ticketCommand) {
    if(ticketCommand == null) {
      return null;
    }

    return Ticket.builder()
        .id(ticketCommand.getId())
        .category(categoryCommandToCategory.convert(ticketCommand.getCategory()))
        .name(ticketCommand.getName())
        .date(ticketCommand.getDate())
        .description(ticketCommand.getDescription())
        .submitter(submitterCommandToSubmitter.convert(ticketCommand.getSubmitter()))
        .park(parkCommandToPark.convert(ticketCommand.getPark()))
        .build();
  }
}
