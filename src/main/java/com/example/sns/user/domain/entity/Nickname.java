package com.example.sns.user.domain.entity;

import com.example.sns.user.domain.validator.NicknameValidatorFactory;
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
    private NicknameValidatorFactory factory = new NicknameValidatorFactory();

    @Builder
    public Nickname(String nickname) {
        factory.blankValidator().execute(nickname);
        factory.lengthValidator().execute(nickname, LENGTH_MIN, LENGTH_MAX);
        this.nickname = nickname;
    }

    public void edit(Nickname to) {
        factory.duplicateValidator().execute(to, this);
        this.nickname = to.getNicknameToString();
    }


    public String getNicknameToString() {
        return this.nickname;
    }
}
