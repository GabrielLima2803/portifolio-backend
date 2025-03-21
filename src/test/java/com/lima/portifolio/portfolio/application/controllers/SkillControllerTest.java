package com.lima.portifolio.portfolio.application.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lima.portifolio.portfolio.application.dtos.SkillRequestDTO;
import com.lima.portifolio.portfolio.application.dtos.SkillResponseDTO;
import com.lima.portifolio.portfolio.application.services.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(SkillController.class)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SkillService skillService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createSkill_ShouldReturnCreatedWithLocationHeader() throws Exception {
        SkillRequestDTO request = new SkillRequestDTO("Java");
        SkillResponseDTO response = new SkillResponseDTO(1L, "Java");
        
        when(skillService.createSkill(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/skills/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    void findAllSkills_ShouldReturn200WithSkills() throws Exception {
        SkillResponseDTO skill = new SkillResponseDTO(1L, "Spring");
        when(skillService.findAllSkill()).thenReturn(List.of(skill));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Spring"));
    }

    @Test
    void findAllSkills_ShouldReturn204WhenEmpty() throws Exception {
        when(skillService.findAllSkill()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/skills"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteSkill_ShouldReturn204NoContent() throws Exception {
        Long skillId = 1L;
        doNothing().when(skillService).deleteSkill(skillId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/skills/{id}", skillId))
                .andExpect(status().isNoContent());
        
        verify(skillService).deleteSkill(skillId);
    }

    
}