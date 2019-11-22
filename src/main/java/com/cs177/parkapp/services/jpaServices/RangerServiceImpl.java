package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.repositories.RangerRepository;
import com.cs177.parkapp.services.RangerService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RangerServiceImpl implements RangerService {

  private final RangerRepository rangerRepository;

  public RangerServiceImpl(RangerRepository rangerRepository) {
    this.rangerRepository = rangerRepository;
  }

  @Override
  public Set<Ranger> getRangers() {
    return new HashSet<Ranger>(rangerRepository.findAll());
  }

  @Override
  public Ranger findById(Long id) {
    return rangerRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException("Ranger id:" + id + " not found")
        );
  }

  @Override
  public Ranger findByEmail(String email) {
    return rangerRepository.findByEmail(email)
        .orElseThrow(
            () -> new EntityNotFoundException("Ranger email:" + email + " not" +
                " found")
        );
  }

  @Override
  public Ranger saveRanger(Ranger ranger) {
    return rangerRepository.save(ranger);
  }
}
