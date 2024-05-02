package com.example.sns.user.domain.validator;

import jakarta.validation.ValidationException;

public interface Validator<T> {
    void validate(T object) throws ValidationException;
}