package com.lima.portifolio.communication.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lima.portifolio.communication.application.dtos.ContactRequestDTO;
import com.lima.portifolio.communication.application.services.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    private ContactRequestDTO createValidRequest() {
        return new ContactRequestDTO(
                "João Silva",
                "joao@example.com",
                "Mensagem detalhada com mais de 10 caracteres"
        );
    }

    @Test
    void sendContact_ShouldReturnSuccessMessage_WhenRequestIsValid() throws Exception {
        ContactRequestDTO request = createValidRequest();
        doNothing().when(contactService).sendContactEmail(any(ContactRequestDTO.class));

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Mensagem enviada com sucesso!"));

        verify(contactService, times(1)).sendContactEmail(any(ContactRequestDTO.class));
    }

    @Test
    void sendContact_ShouldReturnErrorMessage_WhenServiceFails() throws Exception {
        ContactRequestDTO request = createValidRequest();
        doThrow(new RuntimeException("Erro de conexão SMTP"))
                .when(contactService).sendContactEmail(any(ContactRequestDTO.class));

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Falha ao enviar a mensagem."));

        verify(contactService, times(1)).sendContactEmail(any(ContactRequestDTO.class));
    }

    // @Test
    // void sendContact_ShouldReturnBadRequest_WhenInvalidPayload() throws Exception {
    //     String invalidPayload = "{"
    //             + "\"name\": \"\","
    //             + "\"email\": \"email-invalido\","
    //             + "\"message\": \"curta\""
    //             + "}";

    //     mockMvc.perform(post("/api/contacts")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(invalidPayload))
    //             .andExpect(status().isBadRequest())
    //             .andExpect(jsonPath("$.errors").isArray())
    //             .andExpect(jsonPath("$.errors", hasSize(4)));
    // }
}