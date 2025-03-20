package com.lima.portifolio.portfolio.infra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lima.portifolio.portfolio.domain.models.Experience;
import com.lima.portifolio.portfolio.infra.entities.ExperienceJpaEntity;

@Mapper(componentModel = "spring")
public interface ExperiencePersistenceMapper {
    ExperiencePersistenceMapper INSTANCE = Mappers.getMapper(ExperiencePersistenceMapper.class);

    Experience toDomain(ExperienceJpaEntity experienceJpaEntity);

    ExperienceJpaEntity toEntity(Experience experience);
}
