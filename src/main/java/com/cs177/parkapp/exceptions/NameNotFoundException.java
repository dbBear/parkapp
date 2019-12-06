package com.cs177.parkapp.exceptions;

public class NameNotFoundException extends RuntimeException{

  public static final String TITLE = "Name Not Found";

  public NameNotFoundException() {
  }

  public NameNotFoundException(String message) {
    super(message);
  }

  public NameNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
