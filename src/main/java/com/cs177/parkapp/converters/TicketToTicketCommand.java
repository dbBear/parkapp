package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.TicketCommand;
import com.cs177.parkapp.model.Ticket;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketCommand
    implements Converter<Ticket, TicketCommand>
{
  private final CategoryToCategoryCommand categoryToCategoryCommand;
  private final SubmitterToSubmitterCommand submitterToSubmitterCommand;
  private final ParkToParkCommand parkToParkCommand;

  public TicketToTicketCommand(
      @Lazy CategoryToCategoryCommand categoryToCategoryCommand,
      @Lazy SubmitterToSubmitterCommand submitterToSubmitterCommand,
      @Lazy ParkToParkCommand parkToParkCommand)
  {
    this.categoryToCategoryCommand = categoryToCategoryCommand;
    this.submitterToSubmitterCommand = submitterToSubmitterCommand;
    this.parkToParkCommand = parkToParkCommand;
  }

  @Synchronized
  @Nullable
  @Override
  public TicketCommand convert(Ticket ticket) {
    if(ticket == null) {
      return null;
    }

    return TicketCommand.builder()
        .id(ticket.getId())
        .category(categoryToCategoryCommand.convert(ticket.getCategory()))
        .name(ticket.getName())
        .date(ticket.getDate())
        .description(ticket.getDescription())
        .submitter(submitterToSubmitterCommand.convert(ticket.getSubmitter()))
        .park(parkToParkCommand.convert(ticket.getPark()))
        .build();
  }
}
