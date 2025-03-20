package com.lima.portifolio.portfolio.domain.enums;

public enum ProjectStatus {
    IN_PROGRESS("Em processo"),
    FINISHED("Finalizado");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
