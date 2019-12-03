package com.cs177.parkapp.exceptions;

public class RoleNotFoundException extends RuntimeException {
  public RoleNotFoundException() {
    super();
  }

  public RoleNotFoundException(String message) {
    super(message);
  }

  public RoleNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
