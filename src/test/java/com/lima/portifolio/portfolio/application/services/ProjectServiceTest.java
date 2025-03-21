package com.lima.portifolio.portfolio.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.lima.portifolio.portfolio.application.dtos.ProjectRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ProjectResponseDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.mappers.ProjectAppMapper;
import com.lima.portifolio.portfolio.domain.enums.ProjectStatus;
import com.lima.portifolio.portfolio.domain.enums.ProjectType;
import com.lima.portifolio.portfolio.domain.exceptions.ProjectValidationException;
import com.lima.portifolio.portfolio.domain.models.Project;
import com.lima.portifolio.portfolio.domain.models.Skill;
import com.lima.portifolio.portfolio.domain.repositories.ProjectRepository;
import com.lima.portifolio.portfolio.domain.repositories.SkillRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private ProjectAppMapper mapper;

    @InjectMocks
    private ProjectService projectService;

    private final LocalDateTime testDate = LocalDateTime.of(2025, 3, 20, 18, 0);

    @Test
    void createProject_ShouldThrowException_WhenProjectExists() {
        ProjectRequestDTO request = createSampleRequestDTO();
        when(projectRepository.findByName("My Project")).thenReturn(Optional.of(new Project()));

        ProjectValidationException exception = assertThrows(ProjectValidationException.class,
                () -> projectService.createProject(request));

        assertEquals("Project already exists", exception.getMessage());
        verify(projectRepository, never()).save(any());
    }

    @Test
    void createProject_ShouldMapAllFieldsCorrectly() {
        ProjectRequestDTO request = createSampleRequestDTO();
        Project project = createSampleProject();
        ProjectResponseDTO responseDTO = createSampleResponseDTO();

        when(projectRepository.findByName(any())).thenReturn(Optional.empty());
        when(mapper.toDomain(request)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        when(mapper.toResponseDTO(project)).thenReturn(responseDTO);

        ProjectResponseDTO result = projectService.createProject(request);

        assertNotNull(result);
        assertEquals("My Project", result.getName());
        assertEquals(ProjectStatus.IN_PROGRESS, result.getStatus());
        assertEquals(2, result.getSkills().size());
        verify(mapper).toDomain(request);
    }

    @Test
    void findAllProjects_ShouldReturnCompleteResponseStructure() {
        Project project = createSampleProject();
        ProjectResponseDTO responseDTO = createSampleResponseDTO();

        when(projectRepository.findAll()).thenReturn(List.of(project));
        when(mapper.toResponseDTO(project)).thenReturn(responseDTO);

        List<ProjectResponseDTO> result = projectService.findAllProject();

        assertEquals(1, result.size());
        ProjectResponseDTO dto = result.get(0);
        assertEquals("http://github.com/myproject", dto.getUrlGithub());
        assertEquals(testDate, dto.getCreatedAt());
        assertFalse(dto.getSkills().isEmpty());
    }

    // @Test
    // void deleteProject_ShouldHandleRelationships() {
    //     Long projectId = 1L;
    //     doNothing().when(projectRepository).delete(projectId);

    //     projectService.deleteProject(projectId);

    //     verify(projectRepository).delete(projectId);
    //     verify(skillRepository, never()).delete(any());
    // }

    private ProjectRequestDTO createSampleRequestDTO() {
        return new ProjectRequestDTO(
                "My Project",
                "Project description",
                "http://github.com/myproject",
                "http://myproject.com",
                "image.jpg",
                ProjectStatus.IN_PROGRESS,
                ProjectType.Backend,
                testDate.plusMonths(1),
                Set.of(1L, 2L)
        );
    }

    private Project createSampleProject() {
        return new Project(
                1L,
                "My Project",
                "Project description",
                "http://github.com/myproject",
                "http://myproject.com",
                "image.jpg",
                ProjectStatus.IN_PROGRESS,
                ProjectType.Backend,
                testDate.plusMonths(1),
                testDate,
                Set.of(new Skill(), new Skill())
        );
    }

    private ProjectResponseDTO createSampleResponseDTO() {
        return new ProjectResponseDTO(
                1L,
                "My Project",
                "Project description",
                "http://github.com/myproject",
                "http://myproject.com",
                "image.jpg",
                ProjectStatus.IN_PROGRESS,
                ProjectType.Backend,
                testDate.plusMonths(1),
                testDate,
                Set.of(
                        new SkillResponseDTO(1L, "Java"),
                        new SkillResponseDTO(2L, "Spring")
                )
        );
    }
}