package com.cs177.parkapp.services;

import com.cs177.parkapp.dto.ParkDto;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.ParkNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.repositories.ParkRepository;
import com.cs177.parkapp.services.ParkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
//@Transactional
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
          new EmailNotFoundException("Park id:" + id + " not found")
        );
  }

  @Override
  public Park findByName(String name) {
    return parkRepository.findByName(name)
        .orElseThrow(() ->
            new EmailNotFoundException("Park name:" + name + " not found")
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
  public Park newDto(ParkDto parkDto) {
    return parkRepository.save(parkDto.newPark());
  }

  @Override
  public Park updateDto(ParkDto parkDto) {
    Park park = parkRepository.findById(parkDto.getId())
        .orElseThrow(() ->
            new ParkNotFoundException("Park with id: " + parkDto.getId()
                + " not found."));
    park.setName(parkDto.getName());
    return parkRepository.save(park);
  }

  @Override
  public void delete(Park park) {
    parkRepository.delete(park);
  }
}
