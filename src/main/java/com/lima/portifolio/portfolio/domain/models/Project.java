package com.lima.portifolio.portfolio.domain.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.lima.portifolio.portfolio.domain.enums.ProjectStatus;
import com.lima.portifolio.portfolio.domain.enums.ProjectType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    private Long id;
    private String name;
    private String description;
    private String urlGithub;
    private String urlProject;
    private String imageUrl;
    private ProjectStatus status;
    private ProjectType type;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private Set<Skill> skills;
}
