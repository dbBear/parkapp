package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.AnonymousNotFound;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.exceptions.IdNotFoundException;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.repositories.SubmitterRepository;
import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticStrings.ANONYMOUS_EMAIL;
import static com.cs177.parkapp.config.StaticStrings.ANONYMOUS_NAME;

@Service
public class SubmitterServiceImpl implements SubmitterService {

  private final SubmitterRepository submitterRepository;
  private final UserService userService;

  public SubmitterServiceImpl(
      SubmitterRepository submitterRepository,
      @Lazy UserService userService) {
    this.submitterRepository = submitterRepository;
    this.userService = userService;
  }

  @Override
  public Submitter getAnonymousSubmitter() {
    return submitterRepository.findByUserEmail(ANONYMOUS_EMAIL)
        .orElseThrow(() ->
            new AnonymousNotFound("ANONYMOUS EMAIL NOT FOUND!!!!!")
        );
  }

  @Override
  public Set<Submitter> getSubmitters() {
    return new HashSet<>(submitterRepository.findAll());
  }

  @Override
  public Submitter findById(Long id) {
    return submitterRepository.findById(id)
        .orElseThrow(() ->
            new IdNotFoundException("Submitter id:" + id + " not found")
        );
  }

  @Override
  public Submitter findByEmail(String email) {

    if(email.equalsIgnoreCase(ANONYMOUS_EMAIL)
        || email.equalsIgnoreCase(ANONYMOUS_NAME))
    {
      return submitterRepository.findByUserEmail(ANONYMOUS_EMAIL)
          .orElseThrow(AnonymousNotFound::new);
    }

    return submitterRepository.findByUserEmail(email).orElseGet(() -> {
      Submitter submitterNew = Submitter.builder()
          .user(userService.findByEmail(email))
          .build();
      return submitterRepository.save(submitterNew);
    });
  }

  @Override
  public Set<Submitter> findByEmailLike(String email) {
    return new HashSet<>(submitterRepository.findAllByUserEmailLike(email));

  }

  @Override
  public Submitter save(Submitter submitter) {
    return submitterRepository.save(submitter);
  }

  @Override
  public void delete(Submitter submitter) {
    submitterRepository.delete(submitter);
  }
}
