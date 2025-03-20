package com.lima.portifolio.portfolio.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.lima.portifolio.portfolio.domain.models.Skill;

public interface SkillRepository {
    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void delete(Long id);
}
