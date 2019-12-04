package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.repositories.RangerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class RangerServiceImpl implements RangerService {

  private final RangerRepository rangerRepository;

  @Override
  public Set<Ranger> findAll() {
    return new HashSet<>(rangerRepository.findAll());
  }

  @Override
  public Ranger findById(Long id) {
    return rangerRepository.findById(id)
        .orElseThrow(() ->
            new EmailNotFoundException("Ranger id:" + id + " not found")
        );
  }

  @Override
  public Ranger findByEmail(String email) {
    return rangerRepository.findByUserEmail(email)
        .orElseThrow(
            () -> new EmailNotFoundException("Ranger email:" + email + " not" +
                " found")
        );
  }

  @Override
  public Set<Ranger> findByEmailLike(String email) {
    return new HashSet<>(rangerRepository.findAllByUserEmailLike(email));
  }

  @Override
  public Ranger save(Ranger ranger) {
    return rangerRepository.save(ranger);
  }

  @Override
  public void delete(Ranger ranger) {
    rangerRepository.delete(ranger);
  }
}
