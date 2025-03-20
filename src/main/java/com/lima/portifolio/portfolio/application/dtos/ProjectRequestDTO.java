package com.lima.portifolio.portfolio.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

import com.lima.portifolio.portfolio.domain.enums.ProjectStatus;
import com.lima.portifolio.portfolio.domain.enums.ProjectType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequestDTO {
    private String name;
    private String description;
    private String urlGithub;
    private String urlProject;
    private String imageUrl;
    private ProjectStatus status;
    private ProjectType type;
    private LocalDateTime endDate;
    private Set<Long> skillIds; 
}