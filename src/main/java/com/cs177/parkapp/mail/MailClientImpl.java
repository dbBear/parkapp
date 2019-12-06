package com.cs177.parkapp.mail;

import com.cs177.parkapp.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.cs177.parkapp.config.StaticStrings.ANONYMOUS_EMAIL;

@Slf4j
@AllArgsConstructor
@Service
public class MailClientImpl implements MailClient{

  private JavaMailSender mailSender;


  @Override
  public void prepareAndSend(String recipient, String subject, String message) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("no-reply@parkapp.com");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(subject);
      messageHelper.setText(message);
    };

    try{
      mailSender.send(messagePreparator);
    }catch (MailException e) {
      log.debug("YOU GOT AN EXCEPTION: {}", e.getMessage());
    }
  }

  @Override
  public void sendNewTicket(Ticket ticket) {
    String recipient = ticket.getPark().getOfficial().getUser().getEmail();
    String subject = "ParkApp: New Ticket - " + ticket.getName();
    String message =
        "A new ticket is available!!\n" +
        "Name: " + ticket.getName() + "\n" +
        "Description: " + ticket.getDescription();
    prepareAndSend(recipient, subject, message);
  }

  @Override
  public void sendClosedTicket(Ticket ticket) {
    String recipient = ticket.getSubmitter().getUser().getEmail();
    if(recipient.equalsIgnoreCase(ANONYMOUS_EMAIL)){
      String subject = "ParkApp: You Ticket '" + ticket.getName() + "' is " +
          "Closed!";
      String message =
          "Your ticket has been closed!" +
              "Name: " + ticket.getName() +
              "Park: " + ticket.getPark().getName() +
              "Submission: " + ticket.getCreateDateTime() +
              "Description: " + ticket.getDescription();
      prepareAndSend(recipient, subject, message);
    }
  }
}
