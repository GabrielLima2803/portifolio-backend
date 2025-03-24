package com.lima.portifolio.portfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.SkillAppMapper;
import com.lima.portifolio.portfolio.domain.enums.SkillType;
import com.lima.portifolio.portfolio.domain.exceptions.SkillValidationException;
import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillAppMapper mapper;

    public SkillResponseDTO createSkill(SkillRequestDTO request) {
        if (skillRepository.findByName(request.getName()).isPresent()) {
            throw new SkillValidationException("Skill already exists", 400);
        }
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

    public void deleteSkill(Long id) {
        if (skillRepository.findById(id).isEmpty()) {
            throw new SkillValidationException("Skill not found", 404);

        }
        skillRepository.delete(id);
    }

    public List<SkillResponseDTO> findSkillsByType(String type) {
        if (type != null) {
            SkillType skillType;
            try {
                skillType = SkillType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new SkillValidationException("Invalid skill type: " + type, 400);
            }
            return skillRepository.findByType(skillType)
                    .stream()
                    .map(mapper::toResponseDTO)
                    .toList();
        }
        return findAllSkill();
    }
}
