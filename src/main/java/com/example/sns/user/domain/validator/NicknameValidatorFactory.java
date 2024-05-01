package com.example.sns.user.domain.validator;

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
