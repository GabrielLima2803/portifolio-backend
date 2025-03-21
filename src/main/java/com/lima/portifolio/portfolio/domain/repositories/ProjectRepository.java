package com.lima.portifolio.portfolio.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.lima.portifolio.portfolio.domain.models.Project;

public interface ProjectRepository {
    Project save(Project project);
    Optional<Project> findById(Long id);
    List<Project> findAll();
    Optional<Project> findByName(String name);
    void delete(Long id);
}
