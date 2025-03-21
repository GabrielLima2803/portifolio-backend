package com.lima.portifolio.communication.application.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.lima.portifolio.communication.application.dtos.ContactRequestDTO;

import jakarta.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SpringTemplateEngine templateEngine;

    @InjectMocks
    private ContactService contactService;


    private final String testEmail = "test@portfolio.com";

    // @Test
    // void sendContactEmail_ShouldConfigureEmailCorrectly() throws Exception {
    //     ReflectionTestUtils.setField(contactService, "destinationEmail", testEmail);
        
    //     ContactRequestDTO request = new ContactRequestDTO("John Doe", "john@example.com", "Test");
    //     MimeMessage mimeMessage = mailSender.createMimeMessage();
        
    //     when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        
    //     contactService.sendContactEmail(request);
        
    //     ArgumentCaptor<MimeMessage> captor = ArgumentCaptor.forClass(MimeMessage.class);
    //     verify(mailSender).send(captor.capture());
        
    //     MimeMessage sentMessage = captor.getValue();
    //     assertEquals("Novo contato do portfólio de John Doe", sentMessage.getSubject());
    // }

    // @Test
    // void sendContactEmail_ShouldProcessTemplateWithCorrectVariables() throws Exception {
    //     ReflectionTestUtils.setField(contactService, "destinationEmail", testEmail);
        
    //     ContactRequestDTO request = new ContactRequestDTO("Jane Smith", "jane@example.com", "Mensagem");
        
    //     when(templateEngine.process(eq("contact-email"), any(Context.class)))
    //         .thenReturn("<html>Template</html>");
        
    //     MimeMessage mimeMessage = mock(MimeMessage.class);
    //     when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        
    //     contactService.sendContactEmail(request);
        
    //     ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);
    //     verify(templateEngine).process(eq("contact-email"), contextCaptor.capture());
        
    //     Context context = contextCaptor.getValue();
    //     assertEquals("Jane Smith", context.getVariable("name"));
    // }

    @Test
    void sendContactEmail_ShouldHandleEmailSendingErrorsGracefully() throws Exception {
        ReflectionTestUtils.setField(contactService, "destinationEmail", testEmail);
        ContactRequestDTO request = new ContactRequestDTO("Error Test", "error@test.com", "Test");
        
        when(mailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP Error"));

        contactService.sendContactEmail(request);

        verify(mailSender, never()).send((MimeMessage) any());
    }
}