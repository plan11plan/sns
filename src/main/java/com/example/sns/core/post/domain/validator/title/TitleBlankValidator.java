package com.example.sns.core.post.domain.validator.title;

import com.example.sns.core.post.exception.NoValueTitle;
import com.example.sns.core.user.domain.validator.Validator;
import com.example.sns.core.user.exception.nickname.InvalidLengthNickname;

public class TitleBlankValidator implements Validator<String> {
    @Override
    public void validate(String nickname) throws InvalidLengthNickname {
        if (nickname == null || nickname.isBlank()) {
            throw new NoValueTitle();
        }
    }
}