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

import static com.cs177.parkapp.config.StaticNames.*;

@Slf4j
@AllArgsConstructor
@Component
public class ParkAppBootstrap implements CommandLineRunner {

  // string = password
  private static final String
      PASSWORD = "$2a$10$3a66ixKMQ6otrU4qO.DUA.x9isbeunQQ/7mFtHqd/uBy4O5NQurv2";
  // string = admin
  private static final String
      ADMIN_PASSWORD = "$2a$10$KJwv2lUFs/i.I9zWg/ocR.cFh5PKla5KQDEggLM6OAY" +
      ".g7wAHLeBm";

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
    // ROLES
    Role userRole = new Role(1L, ROLE_USER);
    Role rangerRole = new Role(2L, ROLE_RANGER);
    Role officialRole = new Role(3L, ROLE_OFFICIAL);
    Role adminRole = new Role(4L, ROLE_ADMIN);
    roleRepository.saveAll(Arrays.asList(userRole, rangerRole, officialRole,
        adminRole));


    // CATEGORIES
    Category trashCategory = Category.builder()
        .name("Trash")
        .description("Any trash related issues")
        .build();
    Category facilitiesCategory = Category.builder()
        .name("Facilities")
        .description("Any facilities related issues")
        .build();
    Category trailCategory = Category.builder()
        .name("Trail Repair")
        .description("Trails are in need of maintenance")
        .build();
    Category otherCategory = Category.builder()
        .name("Other")
        .description("Other")
        .build();

    categoryRepository.saveAll(Arrays.asList(
        trashCategory, facilitiesCategory, trailCategory, otherCategory
    ));


    // PARKS
    Park park1 = Park.builder()
        .name("Park 1")
        .build();
    parkRepository.save(park1);

    Park park2 = Park.builder()
        .name("Park 2")
        .build();
    parkRepository.save(park2);

    // USERS
    User userAnonymous = User.builder()
        .firstName(ANONYMOUS_NAME)
        .lastName(ANONYMOUS_NAME)
        .email(ANONYMOUS_EMAIL)
        .password(PASSWORD)
        .enabled(true)
        .build();
    userAnonymous.addRole(userRole);

    User userAdmin = User.builder()
        .firstName("admin")
        .lastName("admin")
        .email("admin@admin.com")
        .password(ADMIN_PASSWORD)
        .enabled(true)
        .build();
    userAdmin.addRole(adminRole);

    User userOfficialPark1 = generateUser("official", "park1");
    userOfficialPark1.addRoles(Arrays.asList(userRole, rangerRole));

    User userRangerPark1 = generateUser("ranger", "park1");
    userRangerPark1.addRoles(Arrays.asList(userRole, rangerRole));

    User userOfficialPark2 = generateUser("official", "park2");
    userOfficialPark2.addRoles(Arrays.asList(userRole, rangerRole,
        officialRole));

    User userRangerPark2 = generateUser("ranger", "park2");
    userRangerPark2.addRoles(Arrays.asList(userRole, rangerRole));

    User userSubmitter1 = generateUser("submitter", "1");
    userSubmitter1.addRole(userRole);

    User userSubmitter2 = generateUser("submitter", "2");
    userSubmitter2.addRole(userRole);


    userRepository.saveAll(Arrays.asList(
        userAnonymous, userAdmin, userOfficialPark1, userRangerPark1,
        userOfficialPark2, userRangerPark2, userSubmitter1, userSubmitter2
    ));

    // RANGERS
    Ranger rangerOfficialPark1 = Ranger.builder().user(userOfficialPark1).build();
    park1.addRanger(rangerOfficialPark1);
    park1.setOfficial(rangerOfficialPark1);

    Ranger rangerPark1 = Ranger.builder().user(userRangerPark1).build();
    park1.addRanger(rangerPark1);

    Ranger rangerOfficialPark2 = Ranger.builder().user(userRangerPark1).build();
    park2.addRanger(rangerOfficialPark2);
    park2.setOfficial(rangerOfficialPark2);

    Ranger rangerPark2 = Ranger.builder().user(userRangerPark2).build();
    park2.addRanger(rangerPark2);

    rangerRepository.saveAll(Arrays.asList(
        rangerOfficialPark1, rangerPark1,
        rangerOfficialPark2, rangerPark2
    ));


    // SUBMITTERS
    Submitter anonymousSubmitter = Submitter.builder().user(userAnonymous).build();
    Submitter submitter1 = Submitter.builder().user(userSubmitter1).build();
    Submitter submitter2 = Submitter.builder().user(userSubmitter2).build();
    submitterRepository.saveAll(Arrays.asList(
        anonymousSubmitter, submitter1, submitter2
    ));


    // trash, facilities, trail, other
    // TICKETS
    Ticket ticket1 = generateTicket(
        "Ticket 1", trashCategory,
        anonymousSubmitter, park1
    );

    Ticket ticket2 = generateTicket(
        "Ticket 2", facilitiesCategory,
        anonymousSubmitter, park2
    );

    Ticket ticket3 = generateTicket(
       "Ticket 3", trailCategory,
       submitter1, park1
    );

    Ticket ticket4 = generateTicket(
        "Ticket 4", otherCategory,
        submitter2, park2
    );

    Ticket ticket5 = generateTicket(
        "Ticket 5", trashCategory,
        submitter1, park2
    );
    ticket5.setStatus(Status.CLOSED);

    Ticket ticket6 = generateTicket(
        "Ticket 6", trailCategory,
        submitter2, park1
    );
    ticket6.setStatus(Status.CLOSED);

    ticketRepository.saveAll(Arrays.asList(
        ticket1, ticket2, ticket3, ticket4, ticket5, ticket6
    ));
  }

  private User generateUser(String first, String last) {
    return User.builder()
        .firstName(first)
        .lastName(last)
        .email(first + "." + last + "@email.com")
        .enabled(true)
        .password(PASSWORD)
        .build();
  }

  private Ticket generateTicket(
      String name,
      Category category,
      Submitter submitter,
      Park park
  ) {
    Ticket ticket = Ticket.builder()
        .name(name)
        .status(Status.OPEN)
        .description(name)
        .category(category)
        .submitter(submitter)
        .park(park)
        .build();
    submitter.addTicket(ticket);
    park.addTicket(ticket);
    return ticket;
  }
}
