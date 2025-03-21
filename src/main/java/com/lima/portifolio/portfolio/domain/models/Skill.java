package com.lima.portifolio.portfolio.domain.models;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skill {
    private Long id;
    private String name;
    private Set<Experience> experiences;
    private Set<Project> projects;
}
