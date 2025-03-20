package com.lima.portifolio.portfolio.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.portifolio.portfolio.infra.entities.ExperienceJpaEntity;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceJpaEntity, Long> {
  
} 
