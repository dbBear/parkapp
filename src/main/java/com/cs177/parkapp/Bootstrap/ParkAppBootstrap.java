package com.cs177.parkapp.Bootstrap;

import com.cs177.parkapp.model.*;
import com.cs177.parkapp.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class ParkAppBootstrap implements CommandLineRunner {

  private final OfficialRepository officialRepository;
  private final ParkRepository parkRepository;
  private final SubmitterRepository submitterRepository;
  private final TicketRepository ticketRepository;
  private final CategoryRepository categoryRepository;

  public ParkAppBootstrap(
      OfficialRepository officialRepository,
      ParkRepository parkRepository,
      SubmitterRepository submitterRepository,
      TicketRepository ticketRepository,
      CategoryRepository categoryRepository)
  {
    this.officialRepository = officialRepository;
    this.parkRepository = parkRepository;
    this.submitterRepository = submitterRepository;
    this.ticketRepository = ticketRepository;
    this.categoryRepository = categoryRepository;
  }

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
        .name("The First Park in the Repository!!")
        .build();
    parkRepository.save(park1);

    Official official1 = generateOfficial("Daniel", "Blum");
    park1.addOfficial(official1);
    officialRepository.save(official1);

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

  private Official generateOfficial(String first, String last) {
    return Official.builder()
        .firstName(first)
        .lastName(last)
        .email(first + "." + last + "@official.com")
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
        .date(new Date())
        .build();
  }
}
