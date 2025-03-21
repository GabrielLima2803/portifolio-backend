package com.lima.portifolio.portfolio.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponseDTO>> findAllExperience() {
        List<ExperienceResponseDTO> responseDTOs = experienceService.findAllExperience();
        return responseDTOs.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(responseDTOs);    
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
            experienceService.deleteExperience(id);
            return ResponseEntity.noContent().build();
        }
}
