package com.example.sns.user.domain.validator.password;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.exception.password.InvalidLengthPassword;

public class PasswordLengthValidator {

    public static void execute(String password, int min, int max) {
        if(!isInBoundary(password,min , max)){
            throw  new InvalidLengthPassword();
        }
    }

}
