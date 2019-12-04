package com.cs177.parkapp.services;

import com.cs177.parkapp.exceptions.AnonymousNotFound;
import com.cs177.parkapp.exceptions.EmailNotFoundException;
import com.cs177.parkapp.model.Submitter;
import com.cs177.parkapp.repositories.SubmitterRepository;
import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_EMAIL;
import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_NAME;

@AllArgsConstructor
@Service
public class SubmitterServiceImpl implements SubmitterService {

  private final SubmitterRepository submitterRepository;
  private final UserService userService;
//  private final UserRepository userRepository;

  @Override
  public Submitter getAnonymousSubmitter() {
    return submitterRepository.findByUserEmail(ANONYMOUS_EMAIL)
        .orElseThrow(() ->
            new EmailNotFoundException("ANONYMOUS EMAIL NOT FOUND!!!!!")
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
            new EmailNotFoundException("Submitter id:" + id + " not found")
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

//        .orElseThrow(() ->
//            new EmailNotFoundException("Submitter email:" + email + " not " +
//                "found")
//        );
//    User user = userRepository.findByEmail(email)
//        .orElseThrow(() ->
//            new EmailNotFoundException("User email:" + email + " not found")
//        );
//    return submitterRepository.findByUser(user)
//        .orElseThrow(() ->
//            new EmailNotFoundException("Submitter email:" + email + " not " +
//                "found")
//        );
  }

  @Override
  public Set<Submitter> findByEmailLike(String email) {
//    List<Long> userIds = userRepository.findAllByEmailLike(email).stream()
//        .map(User::getId)
//        .collect(Collectors.toList());
//    return new HashSet<>(submitterRepository.findByIdIn(userIds));
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
