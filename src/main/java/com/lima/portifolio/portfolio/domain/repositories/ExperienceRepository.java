package com.lima.portifolio.portfolio.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.lima.portifolio.portfolio.domain.models.Experience;

public interface ExperienceRepository {
    Experience save(Experience experience);
    Optional<Experience> findById(Long id);
    List<Experience> findAll();
    void delete(Long id);
}
