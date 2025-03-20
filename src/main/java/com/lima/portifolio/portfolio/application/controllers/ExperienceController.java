package com.lima.portifolio.portfolio.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.portifolio.portfolio.application.dtos.ExperienceRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ExperienceResponseDTO;
import com.lima.portifolio.portfolio.application.services.ExperienceService;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(@RequestBody ExperienceRequestDTO requestDTO) {
        ExperienceResponseDTO responseDTO = experienceService.createExperience(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponseDTO>> findAllExperience() {
        List<ExperienceResponseDTO> responseDTOs = experienceService.findAllExperience();
        return ResponseEntity.ok(responseDTOs);
    }
}
