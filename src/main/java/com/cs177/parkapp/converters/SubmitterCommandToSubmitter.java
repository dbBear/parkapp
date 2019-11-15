package com.cs177.parkapp.converters;


import com.cs177.parkapp.commands.SubmitterCommand;
import com.cs177.parkapp.model.Submitter;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SubmitterCommandToSubmitter
  implements Converter<SubmitterCommand, Submitter>
{
  private final TicketCommandToTicket ticketCommandToTicket;

  public SubmitterCommandToSubmitter(
      @Lazy TicketCommandToTicket ticketCommandToTicket)
  {
    this.ticketCommandToTicket = ticketCommandToTicket;
  }

  @Synchronized
  @Nullable
  @Override
  public Submitter convert(SubmitterCommand submitterCommand) {
    if(submitterCommand == null) {
      return null;
    }

    Submitter submitter = Submitter.builder()
        .id(submitterCommand.getId())
        .firstName(submitterCommand.getFirstName())
        .lastName(submitterCommand.getLastName())
        .email(submitterCommand.getEmail())
        .build();

    if(submitterCommand.getTickets() != null && submitterCommand.getTickets().size() > 0) {
      submitterCommand.getTickets().forEach(
          ticketCommand ->
              submitter.getTickets()
                  .add(ticketCommandToTicket.convert(ticketCommand))
      );
    }
    return submitter;
  }
}
