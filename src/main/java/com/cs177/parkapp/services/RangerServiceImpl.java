package com.cs177.parkapp.services;

import com.cs177.parkapp.dto.NewRangerDto;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.repositories.RangerRepository;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.RoleService;
import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticNames.ROLE_RANGER;

@AllArgsConstructor
@Service
public class RangerServiceImpl implements RangerService {

  private final RangerRepository rangerRepository;
  private final ParkService parkService;
  private final RoleService roleService;

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
  public Set<Ranger> findByPark(Park park) {
    return new HashSet<>(rangerRepository.findAllByPark(park));
  }

  @Override
  public Ranger save(Ranger ranger) {
    //todo double check that if you update a rangers park, this saves it in
    // the park table
    return rangerRepository.save(ranger);
  }

  @Override
  public Ranger newSave(User user, NewRangerDto rangerDto) {
    Park park = parkService.findById(rangerDto.getParkId());
    user.getRoles().add(roleService.findByName(ROLE_RANGER));
    Ranger rangerDetached = Ranger.builder()
        .user(user)
        .build();

    rangerDetached.setPark(park);
    Ranger rangerSaved = rangerRepository.save(rangerDetached);
    parkService.save(park);
    return rangerSaved;
  }

  @Override
  public void delete(Ranger ranger) {
    rangerRepository.delete(ranger);
  }
}
