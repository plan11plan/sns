package com.example.sns.core.user.domain.entity.request;

import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.UserId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistoryCreate {
    private final UserId userId;
    private final Nickname nickname;

    @Builder
    public NicknameHistoryCreate(UserId userId, Nickname nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
