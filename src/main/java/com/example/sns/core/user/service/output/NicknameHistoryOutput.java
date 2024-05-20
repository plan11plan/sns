package com.example.sns.core.user.service.output;

import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistoryOutput {
    private final Long id;

    private final Long userId;
    private final Nickname nickname;
    private final LocalDateTime createdAt;


    @Builder
    public NicknameHistoryOutput(Long id, Long userId, Nickname nickname, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
    public static NicknameHistoryOutput from(NicknameHistory nicknameHistory){
       return NicknameHistoryOutput.builder()
                .id(nicknameHistory.getId())
                .userId(nicknameHistory.getUserId())
                .nickname(nicknameHistory.getNickname())
                .createdAt(nicknameHistory.getCreatedAt())
                .build();
    }

}
