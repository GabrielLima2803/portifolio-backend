package com.lima.portifolio.communication.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.lima.portifolio.communication.application.dtos.ContactRequestDTO;

import jakarta.mail.internet.MimeMessage;

@Service
public class ContactService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${app.mail.sender}")
    private String destinationEmail;
    
    @Async
    public void sendContactEmail(ContactRequestDTO contact) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(destinationEmail);
            helper.setReplyTo(contact.getEmail());            
            helper.setTo(destinationEmail);
            helper.setSubject("Novo contato do portfólio de " + contact.getName());

            Context context = new Context();
            context.setVariable("name", contact.getName());
            context.setVariable("email", contact.getEmail());
            context.setVariable("message", contact.getMessage());

            String htmlContent = templateEngine.process("contact-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
