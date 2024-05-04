package com.example.sns.core.post.domain.validator;

import jakarta.validation.ValidationException;

public interface Validator<T> {
    void validate(T object) throws ValidationException;
}