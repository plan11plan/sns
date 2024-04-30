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

    private String nickname;

    @Builder
    public Nickname(String nickname) {
//        validateNickname(nickname);
        this.nickname = nickname;
    }

    public void changeNickname(Nickname to) {
        Objects.requireNonNull(to);
        validateNickname(to);
        this.nickname = to.getNickname();
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
    private void validateNickname(Nickname nickname) {
        Assert.isTrue(nickname.getNickname().length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }

    private boolean isSameNickname(Nickname to){
        return nickname.equals(to.getNickname());
    }

    public String getNicknameToString(){
        return this.nickname;
    }
}
