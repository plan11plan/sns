package com.example.sns.user.domain.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

/**
 * [생성,변경] 닉네임은 최소 2글자 ~ 20글자 이하로 생성된다.
 * [생성,변경] 다른 닉네임들과 같을 수 없다.
 * [변경]닉네임을 변경할 수 있다.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Nickname {
    private final static Long NAME_MAX_LENGTH = 20L;
    private final static Long NAME_MIN_LENGTH = 2L;

    private String nickname;

    @Builder
    public Nickname(String nickname) {
        Objects.requireNonNull(nickname);
        validateMaxLengthNickname(nickname);
        validateMinLengthNickname(nickname);
        this.nickname = nickname;
    }

    public void changeNickname(Nickname to) {
        Objects.requireNonNull(to);
        this.nickname = to.getNickname();
    }

    private void validateMaxLengthNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "20글자 이하로 입력해주세요..");
    }
    private void validateMinLengthNickname(String nickname) {
        Assert.isTrue(nickname.length() >= NAME_MIN_LENGTH, "2글자 이상으로 입력해주세요");
    }

    private boolean isSameNickname(Nickname to){
        return nickname.equals(to.getNickname());
    }

    public String getNicknameToString(){
        return this.nickname;
    }
}
