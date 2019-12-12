package com.cs177.parkapp.services;

import com.cs177.parkapp.dto.NewRangerDto;
import com.cs177.parkapp.dto.UpdateRangerDto;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.repositories.RangerRepository;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.service.RoleService;
import com.cs177.parkapp.security.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticStrings.ROLE_OFFICIAL;
import static com.cs177.parkapp.config.StaticStrings.ROLE_RANGER;

@Service
public class RangerServiceImpl implements RangerService {

  private final RangerRepository rangerRepository;
  private final ParkService parkService;
  private final UserService userService;
  private final RoleService roleService;


  public RangerServiceImpl(
      RangerRepository rangerRepository,
      @Lazy ParkService parkService,
      @Lazy UserService userService,
      @Lazy RoleService roleService) {
    this.rangerRepository = rangerRepository;
    this.parkService = parkService;
    this.userService = userService;
    this.roleService = roleService;
  }

  @Override
  public Set<Ranger> findAll() {
    return new HashSet<>(rangerRepository.findAll());
  }

  @Override
  public Ranger findById(Long id) {
    return rangerRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("Ranger id:" + id + " not found")
        );
  }

  @Override
  public Ranger findByEmail(String email) {
    return rangerRepository.findByUserEmail(email)
        .orElseThrow(() ->
            new EmailNotFoundException("Ranger email:" + email + " not found")
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
  public Ranger update(UpdateRangerDto rangerDto) {
    Ranger rangerDetached = rangerRepository.findById(rangerDto.getId())
        .orElseThrow(() ->
            new IdNotFoundException("Ranger id: " + rangerDto.getId() + " not" +
                " found"));

    Park park = parkService.findById(rangerDto.getParkId());
    if(!rangerDetached.getPark().equals(park)){
      rangerDetached.getPark().removeRanger(rangerDetached);
      park.addRanger(rangerDetached);
    }

    User user = rangerDetached.getUser();
    user.setFirstName(rangerDto.getFirstName());
    user.setLastName(rangerDto.getLastName());
    user.setEmail(rangerDto.getEmail());
    userService.save(user);
    return rangerRepository.findByUser(user)
        .orElseThrow(() ->
            new IdNotFoundException("Ranger with user id: "
                + user.getId() + " not found"));
  }

  @Override
  public void delete(Ranger ranger) {
    removeRangerRoles(ranger);

    rangerRepository.delete(ranger);
  }

  @Override
  public void removeRangerRoles(Ranger ranger) {
    if( ranger.getPark() != null &&
        ranger.getUser().getRoles()
            .contains(roleService.findByName(ROLE_OFFICIAL)))
    {
      ranger.getPark().setOfficial(null);
    }
    User user = userService.findById(ranger.getUser().getId());
    user.setRanger(null);
    ranger.setUser(null);

    Set<Role> rolesToRemove = new HashSet<>();
    Role roleRanger = roleService.findByName(ROLE_RANGER);
    Role roleOfficial = roleService.findByName(ROLE_OFFICIAL);
    rolesToRemove.add(roleRanger);
    if(user.getRoles().contains(roleOfficial)) {
      rolesToRemove.add(roleOfficial);
    }
    userService.removeRoles(user, rolesToRemove);

  }
}
