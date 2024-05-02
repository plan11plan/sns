package com.example.sns.user.domain.validator.password;

import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.password.InvalidLengthPassword;

public class PasswordBlankValidator implements Validator<String> {

    @Override
    public void validate(String password) {
        if (password.isBlank()) {
            throw new InvalidLengthPassword();
        }
    }
}
