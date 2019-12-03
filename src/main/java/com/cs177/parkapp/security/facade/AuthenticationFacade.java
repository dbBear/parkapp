package com.cs177.parkapp.security.facade;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

public interface AuthenticationFacade {
  Authentication getAuthentication();
}
