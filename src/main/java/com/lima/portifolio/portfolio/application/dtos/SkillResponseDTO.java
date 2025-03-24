package com.lima.portifolio.portfolio.application.dtos;

import com.lima.portifolio.portfolio.domain.enums.SkillType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillResponseDTO {
    private Long id;
    private String name;
    private SkillType type;
}