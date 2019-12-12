package com.cs177.parkapp.services;

import com.cs177.parkapp.dto.NewRangerDto;
import com.cs177.parkapp.dto.UpdateRangerDto;
import com.cs177.parkapp.model.Park;
import com.cs177.parkapp.model.Ranger;
import com.cs177.parkapp.security.entity.User;

import java.util.Set;

public interface RangerService {
  Set<Ranger> findAll();
  Ranger findById(Long id);
  Ranger findByEmail(String email);
  Set<Ranger> findByEmailLike(String email);
  Set<Ranger> findByPark(Park park);
  Ranger save(Ranger ranger);
  Ranger newSave(User user, NewRangerDto rangerDto);
  Ranger update(UpdateRangerDto rangerDto);
  void delete(Ranger ranger);
  void removeRangerRoles(Ranger ranger);
}
