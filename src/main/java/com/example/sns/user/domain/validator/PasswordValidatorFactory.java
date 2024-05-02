package com.example.sns.user.domain.validator;

import com.example.sns.user.domain.entity.Password;
import com.example.sns.user.domain.validator.password.PasswordBlankValidator;
import com.example.sns.user.domain.validator.password.PasswordLengthValidator;

public class PasswordValidatorFactory {
    public static Validator<String> lengthValidator() {
        return new PasswordLengthValidator(Password.LENGTH_MIN, Password.LENGTH_MAX);
    }

    public static Validator<String> blankValidator() {
        return new PasswordBlankValidator();
    }

}
