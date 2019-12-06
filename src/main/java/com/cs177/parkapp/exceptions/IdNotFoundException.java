package com.cs177.parkapp.exceptions;

public class IdNotFoundException extends RuntimeException{

  public static final String TITLE = "ID Not Found";

  public IdNotFoundException() {
  }

  public IdNotFoundException(String message) {
    super(message);
  }

  public IdNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
