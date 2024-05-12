package com.example.sns.core.user.domain.entity;

import static com.example.sns.core.user.domain.validator.NicknameValidatorFactory.duplicateValidator;

import com.example.sns.core.common.util.Pair;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [생성,변경] 닉네임은 최소 2글자 ~ 20글자 이하로 생성된다. [생성,변경] 다른 닉네임들과 같을 수 없다. [변경]닉네임을 변경할 수 있다.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Nickname {
    public static final int LENGTH_MIN = 2;
    public static final int LENGTH_MAX = 20;
    private String nickname;

    @Builder
    public Nickname(String nickname)  {
//        blankValidator().validate(nickname);
//        lengthValidator().validate(nickname);
        this.nickname = nickname;
    }

    public void editTo(Nickname to) {
        duplicateValidator().validate(new Pair<>(to,this));
        this.nickname = to.getValue();
    }

    public String getValue() {
        return this.nickname;
    }

}
