package com.example.sns.user.domain.validator.nickname;

import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.nickname.InvalidLengthNickname;

public class NicknameBlankValidator implements Validator<String> {
    @Override
    public void validate(String nickname) throws InvalidLengthNickname {
        if (nickname == null || nickname.isBlank()) {
            throw new InvalidLengthNickname(Nickname.LENGTH_MIN,Nickname.LENGTH_MAX);
        }
    }
}