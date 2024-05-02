package com.example.sns.user.exception.password;

import com.example.sns.common.exception.RootException;
import com.example.sns.user.domain.entity.Password;

/**
 * status : 400
 */
public class InvalidLengthPassword extends RootException {

    private static final String MESSAGE = Password.LENGTH_MIN+"글자 이상" +","+Password.LENGTH_MAX+" 글자 이하로 입력해주세요.";

    public InvalidLengthPassword() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }
}
