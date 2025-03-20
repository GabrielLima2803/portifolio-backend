package com.lima.portifolio.portfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.SkillAppMapper;
import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillAppMapper mapper;

    public SkillResponseDTO createSkill(SkillRequestDTO request) {
        Skill skill = mapper.toDomain(request);
        Skill savedSkill = skillRepository.save(skill);
        return mapper.toResponseDTO(savedSkill);
    }

    public List<SkillResponseDTO> findAllSkill() {
        return skillRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }
}
