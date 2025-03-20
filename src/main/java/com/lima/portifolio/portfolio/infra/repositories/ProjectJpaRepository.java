package com.lima.portifolio.portfolio.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.portifolio.portfolio.infra.entities.ProjectJpaEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {  
} 