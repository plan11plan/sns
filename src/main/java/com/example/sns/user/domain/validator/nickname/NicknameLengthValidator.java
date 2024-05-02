package com.example.sns.user.domain.validator.nickname;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import com.example.sns.user.domain.validator.Validator;
import com.example.sns.user.exception.nickname.InvalidLengthNickname;

public class NicknameLengthValidator implements Validator<String> {
    private final int min;
    private final int max;

    // 생성자를 통해 길이 제한을 설정
    public NicknameLengthValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String nickname) throws InvalidLengthNickname {
        if (nickname == null || !isInBoundary(nickname, min, max)) {
            throw new InvalidLengthNickname(min, max);
        }
    }

}
