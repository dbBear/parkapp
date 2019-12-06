package com.cs177.parkapp.exceptions;

public class RangerNotFoundException extends RuntimeException {
  public RangerNotFoundException() {
    super();
  }

  public RangerNotFoundException(String message) {
    super(message);
  }

  public RangerNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
