package com.lima.portifolio.portfolio.application.utils;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;

@Component
public class SkillMapperUtils {
    @Autowired
    private SkillRepository skillRepository;

    @Named("mapSkillIdsToSkills")
    public Set<Skill> mapSkillIdsToSkills(Set<Long> skillIds) {
        return skillIds.stream()
                .map(skillRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
