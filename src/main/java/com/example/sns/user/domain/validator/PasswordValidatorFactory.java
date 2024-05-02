package com.example.sns.user.domain.validator;

import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.nickname.NicknameLengthValidator;
import com.example.sns.user.domain.validator.password.PasswordLengthValidator;

public class PasswordValidatorFactory {
    public static Validator<String> lengthValidator() {
        return new NicknameLengthValidator(Nickname.LENGTH_MIN, Nickname.LENGTH_MAX);
    }

    public static Validator<String> blankValidator() {
        return new PasswordLengthValidator();
    }

}
