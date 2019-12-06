package com.cs177.parkapp.mail;

import com.cs177.parkapp.model.Ticket;

public interface MailClient {
  public void prepareAndSend(String recipient, String subject, String message);
  public void sendNewTicket(Ticket ticket);
  public void sendClosedTicket(Ticket ticket);
}
