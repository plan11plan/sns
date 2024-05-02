package com.example.sns.user.domain.validator.password;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.domain.entity.Password;
import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.password.InvalidLengthPassword;
import lombok.Builder;

public class PasswordLengthValidator implements Validator<String> {
    private final int min;
    private final int max;

    @Builder
    public PasswordLengthValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String password) {
        if (!isInBoundary(password, Password.LENGTH_MIN, Password.LENGTH_MAX)) {
            throw new InvalidLengthPassword();
        }
    }

}
