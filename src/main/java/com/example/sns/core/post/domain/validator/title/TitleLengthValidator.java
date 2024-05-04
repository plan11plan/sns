package com.example.sns.core.post.domain.validator.title;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.core.post.exception.InvalidLengthTitle;
import com.example.sns.core.user.domain.validator.Validator;
import com.example.sns.core.user.exception.nickname.InvalidLengthNickname;

public class TitleLengthValidator implements Validator<String> {
    private final int min;
    private final int max;

    // 생성자를 통해 길이 제한을 설정
    public TitleLengthValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String input) throws InvalidLengthNickname {
        if (input == null || !isInBoundary(input, min, max)) {
            throw new InvalidLengthTitle(min, max);
        }
    }

}
