package com.lima.portifolio.portfolio.application.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExperienceRequestDTO {
    private String company;
    private String role;
    private String description;
    private LocalDateTime startDate;
    private Set<Long> skillIds;
    private LocalDateTime endDate;
}