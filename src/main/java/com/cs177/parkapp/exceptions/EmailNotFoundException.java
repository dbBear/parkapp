package com.cs177.parkapp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotFoundException extends RuntimeException{

  public EmailNotFoundException() {
    super();
  }

  public EmailNotFoundException(String message) {
    super(message);
    log.error(message);
  }

  public EmailNotFoundException(String message, Throwable cause) {
    super(message, cause);
    log.error(message);
  }
}
