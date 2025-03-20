package com.lima.portifolio.portfolio.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
    IN_PROGRESS(1L, "Em processo"),
    FINISHED(2L, "Finalizado");

    private final long id;
    private final String status;

    ProjectStatus(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static ProjectStatus fromValue(String value) {
        for (ProjectStatus ps : ProjectStatus.values()) {
            if (String.valueOf(ps.getId()).equals(value)
                    || ps.name().equalsIgnoreCase(value)
                    || ps.getStatus().equalsIgnoreCase(value)) {
                return ps;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }
}
