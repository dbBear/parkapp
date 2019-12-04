package com.cs177.parkapp.security.facade;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Set;

public interface AuthenticationFacade {
  Authentication getAuthentication();
  String getName();
  Set<String> getRoles();
}
