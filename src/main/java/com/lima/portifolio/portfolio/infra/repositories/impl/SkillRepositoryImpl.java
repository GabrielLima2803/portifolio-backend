package com.lima.portifolio.portfolio.infra.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;
import com.lima.portifolio.portfolio.infra.entities.SkillJpaEntity;
import com.lima.portifolio.portfolio.infra.mapper.SkillPersistenceMapper;
import com.lima.portifolio.portfolio.infra.repositories.SkillJpaRepository;

@Repository
public class SkillRepositoryImpl implements SkillRepository {

    @Autowired
    private SkillJpaRepository jpaRepository;

    @Autowired
    private SkillPersistenceMapper mapper;

    @Override
    public Skill save(Skill skill) {
        SkillJpaEntity entity = mapper.toEntity(skill);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Skill> findAll() {
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
