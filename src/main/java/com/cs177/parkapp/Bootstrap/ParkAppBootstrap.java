package com.cs177.parkapp.Bootstrap;

import com.cs177.parkapp.model.*;
import com.cs177.parkapp.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Component
public class ParkAppBootstrap implements CommandLineRunner {

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



    Ranger ranger1 = generateRanger("Daniel", "Blum");
    park1.addRanger(ranger1);
    rangerRepository.save(ranger1);

    Submitter submitter1 = generateSubmitter("Travis", "Kinkade");
    submitterRepository.save(submitter1);

    Ticket ticket1 = generateTicket("Ticket 1", trashCategory );
    submitter1.addTicket(ticket1);
    park1.addTicket(ticket1);
    ticketRepository.save(ticket1);

    Submitter submitter2 = generateSubmitter("Baby", "Cakes");
    submitterRepository.save(submitter2);

    Ticket ticket2 = generateTicket("Ticket 2", facilitiesCategory);
    submitter2.addTicket(ticket2);
    park1.addTicket(ticket2);
    ticketRepository.save(ticket2);

    Submitter anonymousSubmitter = generateSubmitter("Anonymous", "Anonymous");
    submitterRepository.save(anonymousSubmitter);
  }

  private Ranger generateRanger(String first, String last) {
    return Ranger.builder()
        .firstName(first)
        .lastName(last)
        .email(first + "." + last + "@ranger.com")
        .build();
  }

  private Submitter generateSubmitter(String first, String last) {
    return Submitter.builder()
        .firstName(first)
        .lastName(last)
        .email(first + "." + last + "@email.com")
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
