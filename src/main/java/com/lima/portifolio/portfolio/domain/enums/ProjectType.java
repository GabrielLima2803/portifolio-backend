package com.lima.portifolio.portfolio.domain.enums;

public enum ProjectType {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    FULLSTACK("Fullstack");

    private final String type;

    ProjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
