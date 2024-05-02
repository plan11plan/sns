package com.example.sns.user.domain.validator.nickname;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.exception.password.InvalidLengthPassword;

public class NicknameLengthValidator {

    public void execute(String nickname,int min, int  max) {
       if(!isInBoundary(nickname,min , max)){
           throw new InvalidLengthPassword();
       }
    }


}
