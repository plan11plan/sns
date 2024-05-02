package com.example.sns.user.domain.validator.nickname;

import com.example.sns.user.exception.nickname.InvalidLengthNickname;

public class NicknameBlankValidator  {
    public void execute(String nickname) {
        if (nickname.isBlank()) {
            throw new InvalidLengthNickname();
        }
    }
}
