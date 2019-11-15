package com.cs177.parkapp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFoundException extends RuntimeException{

  public EntityNotFoundException() {
    super();
  }

  public EntityNotFoundException(String message) {
    super(message);
    log.error(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
    log.error(message);
  }
}
