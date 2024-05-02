package com.example.sns.user.domain.validator.nickname;

import com.example.sns.common.util.Pair;
import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.nickname.DuplicateNickname;

public class DuplicateNicknameValidator implements Validator<Pair<Nickname, Nickname>> {
    @Override
    public void validate(Pair<Nickname, Nickname> nicknames) throws DuplicateNickname {
        Nickname to = nicknames.getFirst();
        Nickname nickname = nicknames.getSecond();
        if (nickname.getNicknameToString().equals(to.getNicknameToString())) {
            throw new DuplicateNickname();
        }
    }
}