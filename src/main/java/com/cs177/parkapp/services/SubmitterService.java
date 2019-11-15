package com.cs177.parkapp.services;

import com.cs177.parkapp.model.Submitter;

import java.util.Set;

public interface SubmitterService {
  Set<Submitter> getSubmitters();
  Submitter findById(Long id);
  Submitter findByEmail(String email);
  Set<Submitter> findByEmailLike(String email);

}
