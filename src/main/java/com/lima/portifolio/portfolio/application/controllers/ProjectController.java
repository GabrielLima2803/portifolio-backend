package com.lima.portifolio.portfolio.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.portifolio.portfolio.application.dtos.ProjectRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ProjectResponseDTO;
import com.lima.portifolio.portfolio.application.services.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO requestDTO) {
        ProjectResponseDTO responseDTO = projectService.createProject(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    
    public ResponseEntity<List<ProjectResponseDTO>> findAllProject() {
        List<ProjectResponseDTO> responseDTOs = projectService.findAllProject();
        return ResponseEntity.ok(responseDTOs);
    }
}
