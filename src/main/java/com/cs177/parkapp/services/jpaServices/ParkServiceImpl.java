package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.repositories.ParkRepository;
import com.cs177.parkapp.services.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ParkServiceImpl implements ParkService {

  private final ParkRepository parkRepository;

  public ParkServiceImpl(ParkRepository parkRepository) {
    this.parkRepository = parkRepository;
  }

  @Override
  public Set<Park> getParks() {
    return new HashSet<Park>(parkRepository.findAll());
  }

  @Override
  public Park findById(Long id) {
    return parkRepository.findById(id)
        .orElseThrow(() ->
          new EntityNotFoundException("Park id:" + id + " not found")
        );
  }

  @Override
  public Park findByName(String name) {
    return parkRepository.findByName(name)
        .orElseThrow(() ->
            new EntityNotFoundException("Park name:" + name + " not found")
        );
  }

  @Override
  public Set<Park> findByNameLike(String name) {
    return new HashSet<Park>(parkRepository.findAllByNameLike(name));
  }
}
