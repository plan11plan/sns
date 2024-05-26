package com.example.sns.common.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class SnsException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public SnsException(String message) {
        super(message);
    }

    public SnsException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
