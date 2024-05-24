package com.example.sns.core.user.service.output;

import com.example.sns.core.user.domain.entity.NicknameHistory;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistoryOutput {
    private final Long id;

    private final Long userId;
    private final String nickname;
    private final LocalDateTime createdAt;


    @Builder
    public NicknameHistoryOutput(Long id, Long userId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
    public static NicknameHistoryOutput from(NicknameHistory nicknameHistory){
       return NicknameHistoryOutput.builder()
                .id(nicknameHistory.getNicknameHistoryIdValue())
                .userId(nicknameHistory.getUserIdValue())
                .nickname(nicknameHistory.getNicknameValue())
                .createdAt(nicknameHistory.getCreatedAt())
                .build();
    }

}
