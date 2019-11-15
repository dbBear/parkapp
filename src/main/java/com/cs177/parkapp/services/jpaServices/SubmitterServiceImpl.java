package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.repositories.SubmitterRepository;
import com.cs177.parkapp.services.SubmitterService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubmitterServiceImpl implements SubmitterService {

  private final SubmitterRepository submitterRepository;

  public SubmitterServiceImpl(SubmitterRepository submitterRepository) {
    this.submitterRepository = submitterRepository;
  }

  @Override
  public Set<Submitter> getSubmitters() {
    return new HashSet<>(submitterRepository.findAll());
  }

  @Override
  public Submitter findById(Long id) {
    return submitterRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException("Submitter id:" + id + " not found")
        );
  }

  @Override
  public Submitter findByEmail(String email) {
    return submitterRepository.findByEmail(email)
        .orElseThrow(() ->
            new EntityNotFoundException("Submitter email:" + email + " not " +
                "found")
        );
  }

  @Override
  public Set<Submitter> findByEmailLike(String email) {
    return new HashSet<>(submitterRepository.findAllByEmailLike(email));
  }
}
