//todo change passwrod to BCrypt with 10 salt
//todo set bootstrap to SQL injects


package com.cs177.parkapp.Bootstrap;

import com.cs177.parkapp.model.*;
import com.cs177.parkapp.repositories.*;
import com.cs177.parkapp.security.entity.Role;
import com.cs177.parkapp.security.entity.User;
import com.cs177.parkapp.security.repository.RoleRepository;
import com.cs177.parkapp.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_EMAIL;
import static com.cs177.parkapp.config.StaticStuff.ANONYMOUS_NAME;

@Slf4j
@AllArgsConstructor
@Component
public class ParkAppBootstrap implements CommandLineRunner {

  private static final String
      PASSWORD = "$2a$10$3a66ixKMQ6otrU4qO.DUA.x9isbeunQQ/7mFtHqd/uBy4O5NQurv2";

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final RangerRepository rangerRepository;
  private final ParkRepository parkRepository;
  private final SubmitterRepository submitterRepository;
  private final TicketRepository ticketRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {
    log.debug("In ParkAppBootstrap");
    loadData();
  }


  private void loadData() {
    Category trashCategory = Category.builder()
        .name("Trash")
        .description("Any trash related issues")
        .build();
    Category facilitiesCategory = Category.builder()
        .name("Facilities")
        .description("Any facilities related issues")
        .build();
    categoryRepository.saveAll(Arrays.asList(trashCategory,
        facilitiesCategory));

    Park park1 = Park.builder()
        .name("Park Number 1")
        .build();
    parkRepository.save(park1);

    Park park2 = Park.builder()
        .name("The Second Park")
        .build();
    parkRepository.save(park2);

    Role userRole = new Role(1L,"ROLE_USER");
    Role rangerRole = new Role(2L,"ROLE_RANGER");
    Role parkRole = new Role(3L,"ROLE_OFFICIAL");
    Role adminRole = new Role(4L, "ROLE_ADMIN");
    roleRepository.saveAll(Arrays.asList(userRole, rangerRole, parkRole,
        adminRole));

    User userAnonymous = User.builder()
        .firstName(ANONYMOUS_NAME)
        .lastName(ANONYMOUS_NAME)
        .email(ANONYMOUS_EMAIL)
        .password(PASSWORD)
        .enabled(true)
        .build();
    userAnonymous.addRole(Arrays.asList(userRole));
    User userRanger = generateUser("Daniel", "Blum");
    userRanger.addRole(Arrays.asList(userRole, rangerRole));
    User user1 = generateUser("Travis", "Kinkade");
    user1.addRole(userRole);
    User user2 = generateUser("Fluffy", "Wuffy");
    user2.addRole(userRole);
    userRepository.save(userAnonymous);
    userRepository.save(userRanger);
    userRepository.save(user1);
    userRepository.save(user2);


    Ranger ranger1 = Ranger.builder().user(userRanger).build();
    park1.addRanger(ranger1);
    rangerRepository.save(ranger1);

    Submitter submitter1 = Submitter.builder().user(user1).build();
    submitterRepository.save(submitter1);

    Ticket ticket1 = generateTicket("Ticket 1", trashCategory );
    submitter1.addTicket(ticket1);
    park1.addTicket(ticket1);
    ticketRepository.save(ticket1);

    Submitter submitter2 = Submitter.builder().user(user2).build();
    submitterRepository.save(submitter2);

    Ticket ticket2 = generateTicket("Ticket 2", facilitiesCategory);
    submitter2.addTicket(ticket2);
    park1.addTicket(ticket2);
    ticketRepository.save(ticket2);

    Submitter anonymousSubmitter = Submitter.builder().user(userAnonymous).build();
    submitterRepository.save(anonymousSubmitter);
  }

  private User generateUser(String first, String last) {
    return User.builder()
        .firstName(first)
        .lastName(last)
        .email(first.toLowerCase() + "." + last.toLowerCase() + "@email.com")
        .enabled(true)
        .password(PASSWORD)
        .build();
  }


  private Ticket generateTicket(String name, Category category) {
    return Ticket.builder()
        .name(name)
        .description(name)
        .category(category)
        .build();
  }
}
