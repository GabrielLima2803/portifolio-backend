package com.lima.portifolio.portfolio.domain.exceptions;


public class SkillValidationException extends RuntimeException {
    private final int statusCode;
    
    public SkillValidationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
