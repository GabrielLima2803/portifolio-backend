package com.lima.portifolio.portfolio.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.lima.portifolio.portfolio.application.dtos.ExperienceRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ExperienceResponseDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.ExperienceAppMapper;
import com.lima.portifolio.portfolio.domain.models.Experience;
import com.lima.portifolio.portfolio.domain.repositories.ExperienceRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private ExperienceAppMapper mapper;

    @InjectMocks
    private ExperienceService experienceService;

    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime startDate = now.minusYears(1);
    private final LocalDateTime endDate = now.plusMonths(6);

    @Test
    void createExperience_ShouldMapAllFieldsCorrectly() {
        ExperienceRequestDTO request = createSampleRequestDTO();
        Experience experience = createSampleExperience();
        ExperienceResponseDTO responseDTO = createSampleResponseDTO();

        when(mapper.toDomain(request)).thenReturn(experience);
        when(experienceRepository.save(experience)).thenReturn(experience);
        when(mapper.toResponseDTO(experience)).thenReturn(responseDTO);

        ExperienceResponseDTO result = experienceService.createExperience(request);

        assertNotNull(result);
        assertEquals("Tech Corp", result.getCompany());
        assertEquals("Senior Developer", result.getRole());
        assertEquals(startDate, result.getStartDate());
        verify(mapper).toDomain(request);
        verify(experienceRepository).save(experience);
    }

    @Test
    void findAllExperiences_ShouldReturnEmptyList() {
        when(experienceRepository.findAll()).thenReturn(Collections.emptyList());

        List<ExperienceResponseDTO> result = experienceService.findAllExperience();

        assertTrue(result.isEmpty());
    }

    @Test
    void findAllExperiences_ShouldReturnMultipleItems() {
        Experience exp1 = createSampleExperience();
        Experience exp2 = createSampleExperience();
        exp2.setId(2L);
        
        when(experienceRepository.findAll()).thenReturn(List.of(exp1, exp2));
        when(mapper.toResponseDTO(exp1)).thenReturn(createSampleResponseDTO());
        when(mapper.toResponseDTO(exp2)).thenReturn(createSampleResponseDTO(2L));

        List<ExperienceResponseDTO> result = experienceService.findAllExperience();

        assertEquals(2, result.size());
        assertEquals(Set.of(1L, 2L), Set.of(result.get(0).getId(), result.get(1).getId()));
    }

    private ExperienceRequestDTO createSampleRequestDTO() {
        return new ExperienceRequestDTO(
            "Tech Corp",
            "Senior Developer",
            "Desenvolvimento de sistemas",
            startDate,
            Set.of(1L, 2L),
            endDate
        );
    }

    private Experience createSampleExperience() {
        Experience experience = new Experience();
        experience.setId(1L);
        experience.setCompany("Tech Corp");
        experience.setRole("Senior Developer");
        experience.setDescription("Desenvolvimento de sistemas");
        experience.setStartDate(startDate);
        experience.setEndDate(endDate);
        experience.setCreatedAt(now);
        return experience;
    }

    private ExperienceResponseDTO createSampleResponseDTO() {
        return createSampleResponseDTO(1L);
    }

    private ExperienceResponseDTO createSampleResponseDTO(Long id) {
        return new ExperienceResponseDTO(
            id,
            "Tech Corp",
            "Senior Developer",
            "Desenvolvimento de sistemas",
            startDate,
            endDate,
            now,
            Set.of(
                new SkillResponseDTO(1L, "Java"),
                new SkillResponseDTO(2L, "Spring")
            )
        );
    }
}