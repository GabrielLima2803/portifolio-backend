package com.lima.portifolio.portfolio.infra.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.lima.portifolio.portfolio.domain.enums.ProjectStatus;
import com.lima.portifolio.portfolio.domain.enums.ProjectType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
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
@Table(name = "project")
public class ProjectJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String urlGithub;
    @Column(nullable = true)
    private String urlProject;
    @Column(nullable = true)
    private String imageUrl;
    private ProjectStatus status;
    private ProjectType type;
    private LocalDateTime endDate;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
        name = "projects_skills", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "skill_id")
        )
    private Set<SkillJpaEntity> skills;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
