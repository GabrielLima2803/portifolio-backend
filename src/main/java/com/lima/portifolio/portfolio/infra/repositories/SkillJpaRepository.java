package com.lima.portifolio.portfolio.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.portifolio.portfolio.infra.entities.SkillJpaEntity;

public interface SkillJpaRepository extends JpaRepository<SkillJpaEntity, Long> {

}
