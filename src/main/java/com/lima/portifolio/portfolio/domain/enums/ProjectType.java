package com.lima.portifolio.portfolio.domain.enums;

public enum ProjectType {
    Backend("Backend"),
    Frontend("Frontend"),
    Fullstack("Fullstack");

    private final String type;

    ProjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
