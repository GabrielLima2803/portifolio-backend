package com.lima.portifolio.portfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.portifolio.portfolio.application.dtos.ProjectRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ProjectResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.ProjectAppMapper;
import com.lima.portifolio.portfolio.domain.exceptions.ProjectValidationException;
import com.lima.portifolio.portfolio.domain.models.Project;
import com.lima.portifolio.portfolio.domain.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectAppMapper mapper;

    public ProjectResponseDTO createProject(ProjectRequestDTO request) {
        if (projectRepository.findByName(request.getName()).isPresent()) {
            throw new ProjectValidationException("Project already exists", 400);
        }

        Project project = mapper.toDomain(request);
        Project savedProject = projectRepository.save(project);
        return mapper.toResponseDTO(savedProject);
    }

    public List<ProjectResponseDTO> findAllProject() {
        return projectRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }
    public void deleteProject(Long id) {
        if (projectRepository.findById(id).isEmpty
        ()) {
            throw new ProjectValidationException("Project not found", 404);
            
        }
        projectRepository.delete(id);
    }   
}
