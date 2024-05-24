package com.example.sns.core.user.domain.entity;

import com.example.sns.core.user.domain.entity.request.NicknameHistoryCreate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistory {

    private final NicknameHistoryId id;

    private final UserId userId;
    private final Nickname nickname;
    private final LocalDateTime createdAt;

    @Builder
    public NicknameHistory(NicknameHistoryId id, UserId userId, Nickname nickname, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
    public static NicknameHistory from(NicknameHistoryCreate nicknameHistoryCreate, LocalDateTime createdAt){
        return NicknameHistory.builder()
                .userId(nicknameHistoryCreate.getUserId())
                .nickname(nicknameHistoryCreate.getNickname())
                .createdAt(createdAt)
                .build();
    }
    public Long getNicknameHistoryIdValue(){
        return this.id != null ? this.id.getValue() : null;
    }
    public Long getUserIdValue(){
        return userId.getValue();
    }
    public String getNicknameValue(){
        return nickname.getValue();
    }


}
