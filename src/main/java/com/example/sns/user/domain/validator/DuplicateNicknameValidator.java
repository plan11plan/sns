package com.example.sns.user.domain.validator;

import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.exception.DuplicateNickname;

public class DuplicateNicknameValidator {

    public void execute(Nickname nickname,Nickname to) {
        if (nickname.getNicknameToString().equals(to.getNicknameToString())) {
            throw new DuplicateNickname();
        }
    }
}
