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

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.services.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(@RequestBody SkillRequestDTO requestDTO) {
        SkillResponseDTO responseDTO = skillService.createSkill(requestDTO);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> findAllSkill() {
        List<SkillResponseDTO> responseDTOs = skillService.findAllSkill();
        return responseDTOs.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(responseDTOs);     
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
