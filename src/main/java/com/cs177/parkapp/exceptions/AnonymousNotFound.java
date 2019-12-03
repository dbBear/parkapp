package com.cs177.parkapp.exceptions;

public class AnonymousNotFound extends RuntimeException{
  public AnonymousNotFound() {
    super("ANONYMOUS USER NOT FOUND");
  }

  public AnonymousNotFound(String message) {
    super(message);
  }

  public AnonymousNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}
