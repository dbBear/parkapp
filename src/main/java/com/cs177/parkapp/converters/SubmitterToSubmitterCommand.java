package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.SubmitterCommand;
import com.cs177.parkapp.model.Submitter;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SubmitterToSubmitterCommand
    implements Converter<Submitter, SubmitterCommand>
{
  private final TicketToTicketCommand ticketToTicketCommand;

  public SubmitterToSubmitterCommand(
      @Lazy TicketToTicketCommand ticketToTicketCommand)
  {
    this.ticketToTicketCommand = ticketToTicketCommand;
  }

  @Synchronized
  @Nullable
  @Override
  public SubmitterCommand convert(Submitter submitter) {
    if (submitter == null) {
      return null;
    }

    SubmitterCommand submitterCommand = SubmitterCommand.builder()
        .id(submitter.getId())
        .firstName(submitter.getFirstName())
        .lastName(submitter.getLastName())
        .email(submitter.getEmail())
        .build();

    if(submitter.getTickets() != null && submitter.getTickets().size() > 0) {
      submitter.getTickets().forEach(
          ticket ->
              submitterCommand.getTickets()
                  .add(ticketToTicketCommand.convert(ticket))
      );
    }
    return submitterCommand;
  }
}
