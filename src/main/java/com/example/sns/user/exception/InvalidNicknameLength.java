package com.example.sns.user.exception;

import com.example.sns.common.exception.RootException;
import com.example.sns.user.domain.entity.Nickname;

/**
 * status : 400
 */
public class InvalidNicknameLength extends RootException {

    private static final String MESSAGE = Nickname.LENGTH_MIN+"글자 이상" +","+Nickname.LENGTH_MAX+" 글자 이하로 입력해주세요.";

    public InvalidNicknameLength() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }
}
