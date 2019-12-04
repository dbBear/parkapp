package com.cs177.parkapp.exceptions;

public class TicketNotFoundException extends RuntimeException{
  public TicketNotFoundException() {
  }

  public TicketNotFoundException(String message) {
    super(message);
  }

  public TicketNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
