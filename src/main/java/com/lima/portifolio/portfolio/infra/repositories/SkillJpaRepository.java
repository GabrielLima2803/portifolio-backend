package com.lima.portifolio.portfolio.infra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.portifolio.portfolio.infra.entities.SkillJpaEntity;

public interface SkillJpaRepository extends JpaRepository<SkillJpaEntity, Long> {
          Optional<SkillJpaEntity> findByName(String name);
}
