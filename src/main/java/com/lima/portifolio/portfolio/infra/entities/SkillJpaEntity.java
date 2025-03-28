package com.lima.portifolio.portfolio.infra.entities;

import java.util.Set;

import com.lima.portifolio.portfolio.domain.enums.SkillType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "skills")
public class SkillJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private SkillType type;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<ProjectJpaEntity> projects;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<ExperienceJpaEntity> experiences;
}
