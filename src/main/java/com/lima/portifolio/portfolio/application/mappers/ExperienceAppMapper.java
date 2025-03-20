package com.lima.portifolio.portfolio.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lima.portifolio.portfolio.application.dtos.ExperienceRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ExperienceResponseDTO;
import com.lima.portifolio.portfolio.application.utils.SkillMapperUtils;
import com.lima.portifolio.portfolio.domain.models.Experience;

@Mapper(componentModel = "spring", uses = SkillMapperUtils.class)
public interface ExperienceAppMapper {
    ExperienceAppMapper INSTANCE = Mappers.getMapper(ExperienceAppMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "skills", source = "skillIds", qualifiedByName = "mapSkillIdsToSkills") 
    Experience toDomain(ExperienceRequestDTO dto);
    
    ExperienceResponseDTO toResponseDTO(Experience domain);

}
