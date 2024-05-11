package com.example.sns.core.user.domain.validator.nickname;

import com.example.sns.core.common.util.Pair;
import com.example.sns.core.user.domain.validator.Validator;
import com.example.sns.core.user.exception.nickname.DuplicateNickname;
import com.example.sns.core.user.domain.entity.Nickname;

public class DuplicateNicknameValidator implements Validator<Pair<Nickname, Nickname>> {
    @Override
    public void validate(Pair<Nickname, Nickname> nicknames) throws DuplicateNickname {
        Nickname to = nicknames.getFirst();
        Nickname nickname = nicknames.getSecond();
        if (nickname.getValue().equals(to.getValue())) {
            throw new DuplicateNickname();
        }
    }
}