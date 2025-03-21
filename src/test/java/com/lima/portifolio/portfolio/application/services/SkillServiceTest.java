package com.lima.portifolio.portfolio.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.SkillAppMapper;
import com.lima.portifolio.portfolio.domain.exceptions.SkillValidationException;
import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {
      @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillAppMapper mapper;

    @InjectMocks
    private SkillService skillService;

    @Test
    void createSkill_ShouldThrowException_WhenSkillExists() {
        SkillRequestDTO request = new SkillRequestDTO("Java");
        when(skillRepository.findByName("Java")).thenReturn(Optional.of(new Skill()));

        SkillValidationException exception = assertThrows(SkillValidationException.class,
            () -> skillService.createSkill(request));

        assertEquals("Skill already exists", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
        verify(skillRepository, never()).save(any(Skill.class));
    }

    @Test
    void createSkill_ShouldSaveSkill_WhenValidRequest() {
        SkillRequestDTO request = new SkillRequestDTO("Java");
        Skill domain = new Skill(1L, "Java", null, null);
        SkillResponseDTO responseDTO = new SkillResponseDTO(1L, "Java");

        when(skillRepository.findByName("Java")).thenReturn(Optional.empty());
        when(mapper.toDomain(request)).thenReturn(domain);
        when(skillRepository.save(domain)).thenReturn(domain);
        when(mapper.toResponseDTO(domain)).thenReturn(responseDTO);

        SkillResponseDTO result = skillService.createSkill(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(skillRepository).save(domain);
        verify(mapper).toResponseDTO(domain);
    }

    @Test
    void findAllSkills_ShouldReturnEmptyList_WhenNoSkillsExist() {
        when(skillRepository.findAll()).thenReturn(Collections.emptyList());

        List<SkillResponseDTO> result = skillService.findAllSkill();

        assertTrue(result.isEmpty());
        verify(skillRepository).findAll();
        verify(mapper, never()).toResponseDTO(any());
    }

    @Test
    void findAllSkills_ShouldReturnMappedDTOs_WhenSkillsExist() {
        Skill skill1 = new Skill(1L, "Java", null, null);
        Skill skill2 = new Skill(2L, "Spring", null, null);
        SkillResponseDTO dto1 = new SkillResponseDTO(1L, "Java");
        SkillResponseDTO dto2 = new SkillResponseDTO(2L, "Spring");

        when(skillRepository.findAll()).thenReturn(List.of(skill1, skill2));
        when(mapper.toResponseDTO(skill1)).thenReturn(dto1);
        when(mapper.toResponseDTO(skill2)).thenReturn(dto2);

        List<SkillResponseDTO> result = skillService.findAllSkill();

        assertEquals(2, result.size());
        assertIterableEquals(List.of(dto1, dto2), result);
        verify(mapper, times(2)).toResponseDTO(any());
    }

    // @Test
    // void deleteSkill_ShouldCallRepositoryDelete() {
    //     Long skillId = 1L;
    //     doNothing().when(skillRepository).delete(skillId);

    //     skillService.deleteSkill(skillId);

    //     verify(skillRepository).delete(skillId);
    //     verifyNoMoreInteractions(skillRepository);
    // }

    @Test
    void deleteSkill_ShouldThrowException_WhenInvalidId() {
        Long invalidId = -1L;
            assertThrows(SkillValidationException.class, () -> {
                skillService.deleteSkill(invalidId);
            });
    }
}
