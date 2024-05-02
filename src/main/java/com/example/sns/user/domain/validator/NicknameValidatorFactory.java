package com.example.sns.user.domain.validator;

import com.example.sns.common.util.Pair;
import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.nickname.DuplicateNicknameValidator;
import com.example.sns.user.domain.validator.nickname.NicknameBlankValidator;
import com.example.sns.user.domain.validator.nickname.NicknameLengthValidator;

public class NicknameValidatorFactory {
    public static Validator<String> lengthValidator() {
        return new NicknameLengthValidator(Nickname.LENGTH_MIN,Nickname.LENGTH_MAX);
    }

    public static Validator<String> blankValidator() {
        return new NicknameBlankValidator();
    }

    public static Validator<Pair<Nickname, Nickname>> duplicateValidator() {
        return new DuplicateNicknameValidator();
    }
}