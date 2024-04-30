package com.example.sns.user.domain.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Embeddable
@Getter
@NoArgsConstructor
public class Nickname {
    private final static Long NAME_MAX_LENGTH = 20L;

    private String nickname;

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
}
