package com.example.sns.user.domain.validator;

import com.example.sns.user.exception.InvalidNicknameLength;

public class NicknameBlankValidator  {
    public void execute(String nickname) {
        if (nickname.isBlank()) {
            throw new InvalidNicknameLength();
        }
    }
}
