package com.lima.portifolio.portfolio.infra.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.infra.entities.SkillJpaEntity;

@Mapper(componentModel = "spring")
public interface SkillPersistenceMapper {
    SkillPersistenceMapper INSTANCE = Mappers.getMapper(SkillPersistenceMapper.class);

    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true) 
    Skill toDomain(SkillJpaEntity skillJpaEntity);
    
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)  
    SkillJpaEntity toEntity(Skill skill);

    default Set<Skill> skillJpaEntitySetToSkillSet(Set<SkillJpaEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }
}
