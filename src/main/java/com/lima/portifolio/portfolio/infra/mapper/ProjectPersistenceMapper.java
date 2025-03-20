package com.lima.portifolio.portfolio.infra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lima.portifolio.portfolio.domain.models.Project;
import com.lima.portifolio.portfolio.infra.entities.ProjectJpaEntity;

@Mapper(componentModel = "spring", uses = SkillPersistenceMapper.class)
public interface ProjectPersistenceMapper {
    ProjectPersistenceMapper INSTANCE = Mappers.getMapper(ProjectPersistenceMapper.class);
    
    @Mapping(target = "skills", source = "skills") 
    Project toDomain(ProjectJpaEntity projectJpaEntity);
    @Mapping(target = "skills", source = "skills") 
    ProjectJpaEntity toEntity(Project project);
}
