package com.lima.portifolio.portfolio.application.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.lima.portifolio.portfolio.application.dtos.ProjectRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ProjectResponseDTO;
import com.lima.portifolio.portfolio.application.utils.SkillMapperUtils;
import com.lima.portifolio.portfolio.domain.models.Project;

@Mapper(componentModel = "spring", uses = SkillMapperUtils.class)
public interface ProjectAppMapper {

    ProjectAppMapper INSTANCE = Mappers.getMapper(ProjectAppMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "skills", source = "skillIds", qualifiedByName = "mapSkillIdsToSkills")
    Project toDomain(ProjectRequestDTO dto);

    ProjectResponseDTO toResponseDTO(Project project);
}
