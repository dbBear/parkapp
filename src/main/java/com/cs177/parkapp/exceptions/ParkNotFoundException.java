package com.cs177.parkapp.exceptions;

public class ParkNotFoundException extends RuntimeException{
  public ParkNotFoundException() {
    super();
  }

  public ParkNotFoundException(String message) {
    super(message);
  }

  public ParkNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
