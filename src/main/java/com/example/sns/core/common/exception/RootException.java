package com.example.sns.core.common.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class RootException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public RootException(String message) {
        super(message);
    }

    public abstract String getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
