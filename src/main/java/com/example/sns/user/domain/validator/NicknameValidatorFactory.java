package com.example.sns.user.domain.validator;

import com.example.sns.user.domain.validator.nickname.DuplicateNicknameValidator;
import com.example.sns.user.domain.validator.nickname.NicknameBlankValidator;
import com.example.sns.user.domain.validator.nickname.NicknameLengthValidator;

public class NicknameValidatorFactory {

    public static NicknameLengthValidator lengthValidator(){
        return new NicknameLengthValidator();
    }
    public static NicknameBlankValidator blankValidator(){
        return new NicknameBlankValidator();
    }
    public static DuplicateNicknameValidator duplicateValidator(){
        return new DuplicateNicknameValidator();
    }
}
