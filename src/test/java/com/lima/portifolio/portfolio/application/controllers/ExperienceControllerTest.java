package com.lima.portifolio.portfolio.application.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lima.portifolio.portfolio.application.dtos.ExperienceRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.ExperienceResponseDTO;
import com.lima.portifolio.portfolio.application.services.ExperienceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ExperienceController.class)
class ExperienceControllerTest {
    private static final String COMPANY_NAME_TECH_CORP = "Tech Corp";
    private static final String COMPANY_NAME_STARTUP_X = "Startup X";
    private static final String ROLE_DEVELOPER = "Dev";
    private static final String ROLE_ENGINEER = "Engenheiro";
    private static final String DESCRIPTION_DEVELOPMENT = "Desenvolvimento";
    private static final String DESCRIPTION_SYSTEMS = "Sistemas";
    private static final String BASE_API_URL = "/api/experiences";

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ExperienceService experienceService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExperience_ShouldReturnCreatedWithLocationHeader() throws Exception {
        ExperienceRequestDTO request = new ExperienceRequestDTO(
            COMPANY_NAME_TECH_CORP,
            ROLE_DEVELOPER,
            DESCRIPTION_DEVELOPMENT,
            LocalDateTime.now(),
            Set.of(1L),
            null
        );
        
        ExperienceResponseDTO response = new ExperienceResponseDTO(
            1L,
            COMPANY_NAME_TECH_CORP,
            ROLE_DEVELOPER,
            DESCRIPTION_DEVELOPMENT,
            LocalDateTime.now(),
            null,
            LocalDateTime.now(),
            Set.of()
        );
        
        when(experienceService.createExperience(any())).thenReturn(response);
        
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost" + BASE_API_URL + "/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.company").value(COMPANY_NAME_TECH_CORP));
    }

    @Test
    void findAllExperiences_ShouldReturn200WithExperiences() throws Exception {
        ExperienceResponseDTO experience = new ExperienceResponseDTO(
            1L,
            COMPANY_NAME_STARTUP_X,
            ROLE_ENGINEER,
            DESCRIPTION_SYSTEMS,
            LocalDateTime.now(),
            null,
            LocalDateTime.now(),
            Set.of()
        );
        
        when(experienceService.findAllExperience()).thenReturn(List.of(experience));
        
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].role").value(ROLE_ENGINEER));
    }

    @Test
    void findAllExperiences_ShouldReturn204WhenEmpty() throws Exception {
        when(experienceService.findAllExperience()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/experiences"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteExperience_ShouldReturn204NoContent() throws Exception {
        Long experienceId = 1L;
        doNothing().when(experienceService).deleteExperience(experienceId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/experiences/{id}", experienceId))
                .andExpect(status().isNoContent());
        
        verify(experienceService).deleteExperience(experienceId);
    }

    // @Test
    // void deleteExperience_ShouldReturn404WhenNotFound() throws Exception {
    //     Long invalidId = 999L;
        
    //     doThrow(new ExperienceValidationException("Experiência com ID 999 não encontrada", 404))
    //         .doNothing().when(experienceService).deleteExperience(invalidId);
    
    //     mockMvc.perform(MockMvcRequestBuilders.delete("/api/experiences/{id}", invalidId))
    //             .andExpect(status().isNotFound())
    //             .andExpect(content().string("Experiência com ID 999 não encontrada"));
    // }
}