package com.cs177.parkapp.exceptions;

public class EmailNotFoundException extends RuntimeException{

  public static final String TITLE = "Email Not Found";

  public EmailNotFoundException() {
    super();
  }

  public EmailNotFoundException(String message) {
    super(message);
  }

  public EmailNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
