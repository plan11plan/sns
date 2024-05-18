package com.example.sns.core.user.domain.entity.vo;

import com.example.sns.core.user.domain.entity.Sex;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {
    private final Long userId;
    private final String nickname;
    private final Sex sex;

    @Builder
    public UserProfile(Long userId, String nickname, Sex sex) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
    }
}
