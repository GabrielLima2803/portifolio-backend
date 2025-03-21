package com.lima.portifolio.portfolio.domain.exceptions;

public class ExperienceValidationException extends RuntimeException {
    private final int statusCode;
    
    public ExperienceValidationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}