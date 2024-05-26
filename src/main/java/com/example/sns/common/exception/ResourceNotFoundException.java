package com.example.sns.common.exception;


public abstract class ResourceNotFoundException extends SnsException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getStatusCode() {
        return "404";
    }
}
