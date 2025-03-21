package com.lima.portifolio.portfolio.application.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lima.portifolio.portfolio.application.dtos.ProjectRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ProjectResponseDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.domain.enums.ProjectStatus;
import com.lima.portifolio.portfolio.domain.enums.ProjectType;
import com.lima.portifolio.portfolio.application.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProjectRequestDTO createSampleRequest() {
        return new ProjectRequestDTO(
                "E-commerce Project",
                "Complete e-commerce solution",
                "https://github.com/ecommerce",
                "https://ecommerce.com",
                "https://image.url",
                ProjectStatus.FINISHED,
                ProjectType.BACKEND,
                LocalDateTime.parse("2023-12-31T23:59:59"),
                Set.of(1L, 2L)
        );
    }

    private ProjectResponseDTO createSampleResponse() {
        return new ProjectResponseDTO(
                1L,
                "E-commerce Project",
                "Complete e-commerce solution",
                "https://github.com/ecommerce",
                "https://ecommerce.com",
                "https://image.url",
                ProjectStatus.FINISHED,
                ProjectType.BACKEND,
                LocalDateTime.parse("2023-12-31T23:59:59"),
                LocalDateTime.now(),
                Set.of(new SkillResponseDTO(1L, "Java"), new SkillResponseDTO(2L, "Spring"))
        );
    }

    @Test
    void createProject_ShouldReturnCreatedResponseWithLocationHeader() throws Exception {
        ProjectRequestDTO requestDTO = createSampleRequest();
        ProjectResponseDTO responseDTO = createSampleResponse();

        when(projectService.createProject(any(ProjectRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/projects/1")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("E-commerce Project")))
                .andExpect(jsonPath("$.status", is("Finalizado")))
                .andExpect(jsonPath("$.type", is("BACKEND")))
                .andExpect(jsonPath("$.skills[*].name", containsInAnyOrder("Java", "Spring")));

        verify(projectService, times(1)).createProject(any(ProjectRequestDTO.class));
    }

    @Test
    void findAllProject_ShouldReturnListOfProjects() throws Exception {
        ProjectResponseDTO responseDTO = createSampleResponse();
        List<ProjectResponseDTO> projects = List.of(responseDTO);

        when(projectService.findAllProject()).thenReturn(projects);

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].urlGithub", is("https://github.com/ecommerce")))
                .andExpect(jsonPath("$[0].endDate", containsString("2023-12-31T23:59:59")))
                .andExpect(jsonPath("$[0].skills", hasSize(2)));
    }

    @Test
    void findAllProject_ShouldReturnNoContentWhenEmpty() throws Exception {
        when(projectService.findAllProject()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProject_ShouldReturnNoContent() throws Exception {
        doNothing().when(projectService).deleteProject(1L);

        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isNoContent());

        verify(projectService, times(1)).deleteProject(1L);
    }
}