package com.lima.portifolio.portfolio.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.portifolio.portfolio.application.dtos.ExperienceRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ExperienceResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.ExperienceAppMapper;
import com.lima.portifolio.portfolio.domain.models.Experience;
import com.lima.portifolio.portfolio.domain.repositories.ExperienceRepository;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceAppMapper experienceAppMapper;

    public ExperienceResponseDTO createExperience(ExperienceRequestDTO request) {
        Experience experience = experienceAppMapper.toDomain(request);
        Experience savedExperience = experienceRepository.save(experience);
        return experienceAppMapper.toResponseDTO(savedExperience);
    }

    public List<ExperienceResponseDTO> findAllExperience() {
        return experienceRepository.findAll()
                .stream()
                .map(experienceAppMapper::toResponseDTO)
                .toList();
    }
}
