package com.lima.portifolio.portfolio.domain.exceptions;

public class ProjectValidationException extends RuntimeException {
    private final int statusCode;
    
    public ProjectValidationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
