package com.example.sns.core.user.domain.validator.password;

import com.example.sns.core.user.domain.validator.Validator;
import com.example.sns.core.user.exception.password.InvalidLengthPassword;

public class PasswordBlankValidator implements Validator<String> {

    @Override
    public void validate(String password) {
        if (password.isBlank()) {
            throw new InvalidLengthPassword();
        }
    }
}
