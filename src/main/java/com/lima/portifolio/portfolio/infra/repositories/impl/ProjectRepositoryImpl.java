package com.lima.portifolio.portfolio.infra.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lima.portifolio.portfolio.domain.models.Project;
import com.lima.portifolio.portfolio.domain.repositories.ProjectRepository;
import com.lima.portifolio.portfolio.infra.entities.ProjectJpaEntity;
import com.lima.portifolio.portfolio.infra.mapper.ProjectPersistenceMapper;
import com.lima.portifolio.portfolio.infra.repositories.ProjectJpaRepository;


@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
     @Autowired
    private ProjectJpaRepository jpaRepository;

    @Autowired
    private ProjectPersistenceMapper mapper;
      @Override
    public Optional<Project> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }
    @Override
    public Project save(Project project) {
        ProjectJpaEntity entity = mapper.toEntity(project);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Project> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Project> findAll() {
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
