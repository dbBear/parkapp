package com.cs177.parkapp.security.facade;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public String getName() {
    return getAuthentication().getName();
  }

  @Override
  public Set<String> getRoles() {
    return getAuthentication().getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
  }
}
