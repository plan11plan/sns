package com.example.sns.user.domain.validator;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.exception.InvalidNicknameLength;

public class NicknameLengthValidator {

    public void execute(String nickname,int min, int  max) {
       if(!isInBoundary(nickname,min , max)){
           throw new InvalidNicknameLength();
       }
    }


}
