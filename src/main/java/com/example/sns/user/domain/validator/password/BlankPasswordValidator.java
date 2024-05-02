package com.example.sns.user.domain.validator.password;

import com.example.sns.user.exception.password.InvalidLengthPassword;

public class BlankPasswordValidator {
    public static void execute(String password) {
        if (password.isBlank()) {
            throw new InvalidLengthPassword();
        }
    }
}
