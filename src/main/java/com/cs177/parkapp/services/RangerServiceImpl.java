package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.repositories.RangerRepository;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.repository.UserRepository;
import com.cs177.parkapp.services.RangerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RangerServiceImpl implements RangerService {

  private final RangerRepository rangerRepository;

  @Override
  public Set<Ranger> getRangers() {
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
    return rangerRepository.findByUserId_Email(email)
        .orElseThrow(
            () -> new EmailNotFoundException("Ranger email:" + email + " not" +
                " found")
        );
  }

  @Override
  public Set<Ranger> findByEmailLike(String email) {
    return new HashSet<>(rangerRepository.findAllByUserId_EmailLike(email));
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
