package com.cs177.parkapp.services.jpaServices;

import com.cs177.parkapp.exceptions.EntityNotFoundException;
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
  private final UserRepository userRepository;

  @Override
  public Set<Ranger> getRangers() {
    return new HashSet<>(rangerRepository.findAll());
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
    User user = userRepository.findByEmail(email);
    return rangerRepository.findByUser(user)
        .orElseThrow(
            () -> new EntityNotFoundException("Ranger email:" + email + " not" +
                " found")
        );
  }

  @Override
  public Set<Ranger> findByEmailLike(String email) {
    List<Long> userIds = userRepository.findAllByEmailLike(email).stream()
        .map(User::getId)
        .collect(Collectors.toList());
    return new HashSet<>(rangerRepository.findByIdIn(userIds));

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
