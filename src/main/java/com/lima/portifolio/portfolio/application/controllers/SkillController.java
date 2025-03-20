package com.lima.portifolio.portfolio.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.services.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    public ResponseEntity<SkillResponseDTO> createSkill(@RequestBody SkillRequestDTO requestDTO) {
        SkillResponseDTO responseDTO = skillService.createSkill(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<List<SkillResponseDTO>> findAllSkill() {
        List<SkillResponseDTO> responseDTOs = skillService.findAllSkill();
        return ResponseEntity.ok(responseDTOs);
    }	
}
