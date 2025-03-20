package com.lima.portifolio.portfolio.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.domain.models.Skill;

@Mapper(componentModel = "spring")
public interface SkillAppMapper {
    SkillAppMapper INSTANCE = Mappers.getMapper(SkillAppMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)
    Skill toDomain(SkillRequestDTO dto);

    SkillResponseDTO toResponseDTO(Skill domain);
}
