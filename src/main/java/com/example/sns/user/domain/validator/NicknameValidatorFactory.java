package com.example.sns.user.domain.validator;

public class NicknameValidatorFactory {

    public NicknameLengthValidator lengthValidator(){
        return new NicknameLengthValidator();
    }
    public NicknameBlankValidator blankValidator(){
        return new NicknameBlankValidator();
    }
    public DuplicateNicknameValidator duplicateValidator(){
        return new DuplicateNicknameValidator();
    }
}
