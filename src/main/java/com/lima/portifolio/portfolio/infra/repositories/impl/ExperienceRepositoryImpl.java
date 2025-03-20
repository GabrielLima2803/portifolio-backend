package com.lima.portifolio.portfolio.infra.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lima.portifolio.portfolio.domain.models.Experience;
import com.lima.portifolio.portfolio.domain.repositories.ExperienceRepository;
import com.lima.portifolio.portfolio.infra.entities.ExperienceJpaEntity;
import com.lima.portifolio.portfolio.infra.mapper.ExperiencePersistenceMapper;
import com.lima.portifolio.portfolio.infra.repositories.ExperienceJpaRepository;

@Repository
public class ExperienceRepositoryImpl implements ExperienceRepository {

    @Autowired
    private ExperienceJpaRepository jpaRepository;

    @Autowired
    private ExperiencePersistenceMapper mapper;

    @Override
    public Experience save(Experience experience) {
        ExperienceJpaEntity entity = mapper.toEntity(experience);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Experience> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

}
