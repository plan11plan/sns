package com.example.sns.core.user.domain.validator;

import com.example.sns.common.util.Pair;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.validator.password.DuplicateCurrentPasswordValidator;
import com.example.sns.core.user.domain.validator.password.PasswordBlankValidator;
import com.example.sns.core.user.domain.validator.password.PasswordLengthValidator;

public interface PasswordValidatorFactory {
    public static Validator<String> lengthValidator() {
        return new PasswordLengthValidator(Password.LENGTH_MIN, Password.LENGTH_MAX);
    }

    public static Validator<String> blankValidator() {
        return new PasswordBlankValidator();
    }

    public static Validator<Pair<Password, Password>> duplicateCurrentPasswordValidator() {
        return new DuplicateCurrentPasswordValidator();
    }

}
