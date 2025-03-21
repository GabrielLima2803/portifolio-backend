package com.lima.portifolio.portfolio.infra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.portifolio.portfolio.infra.entities.ProjectJpaEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {
      Optional<ProjectJpaEntity> findByName(String name);
  
} 