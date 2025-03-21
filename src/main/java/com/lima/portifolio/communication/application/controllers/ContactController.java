package com.lima.portifolio.communication.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.portifolio.communication.application.dtos.ContactRequestDTO;
import com.lima.portifolio.communication.application.services.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendContact(@RequestBody ContactRequestDTO request) {
        try {
            contactService.sendContactEmail(request);
            return ResponseEntity.ok("Mensagem enviada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Falha ao enviar a mensagem.");
        }
    }
}
