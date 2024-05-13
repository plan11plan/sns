package com.example.sns.core.user.domain.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistory {

    private final Long id;

    private final Long userId;
    private final Nickname nickname;
    private final LocalDateTime createdAt;


    @Builder
    public NicknameHistory(Long id, Long userId, Nickname nickname, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }


}
