package com.example.sns.user.domain.validator.password;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.domain.entity.Password;
import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.password.InvalidLengthPassword;

public class PasswordLengthValidator implements Validator<String> {


    @Override
    public void validate(String password) {
        if (!isInBoundary(password, Password.LENGTH_MIN, Password.LENGTH_MAX)) {
            throw new InvalidLengthPassword();
        }
    }

}
