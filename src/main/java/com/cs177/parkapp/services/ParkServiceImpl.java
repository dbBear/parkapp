package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.exceptions.NameNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.repositories.ParkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class ParkServiceImpl implements ParkService {

  private final ParkRepository parkRepository;

  @Override
  public boolean existsById(Long id) {
    return parkRepository.existsById(id);
  }

  @Override
  public Set<Park> findAll() {
    return new HashSet<>(parkRepository.findAll());
  }

  @Override
  public Park findById(Long id) {
    return parkRepository.findById(id)
        .orElseThrow(() ->
          new IdNotFoundException("Park id:" + id + " not found")
        );
  }

  @Override
  public Park findByName(String name) {
    return parkRepository.findByName(name)
        .orElseThrow(() ->
            new NameNotFoundException("Park name:" + name + " not found")
        );
  }

  @Override
  public Set<Park> findByNameLike(String name) {
    return new HashSet<>(parkRepository.findAllByNameLike(name));
  }

  @Override
  public Park save(Park park) {

    return parkRepository.save(park);
  }



  @Override
  public void delete(Park park) {
    parkRepository.delete(park);
  }
}
