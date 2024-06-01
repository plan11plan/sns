package com.example.sns.core.user.infrastructure.persistence.entity;

import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.NicknameHistoryId;
import com.example.sns.core.user.domain.entity.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nicknameHistories")
public class NicknameHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String nickname;
    private LocalDateTime createdAt;

    public static NicknameHistoryEntity from(NicknameHistory nicknameHistory) {
        if (nicknameHistory == null) {
            throw new IllegalArgumentException("NicknameHistory must not be null");
        }

        NicknameHistoryEntity entity = new NicknameHistoryEntity();
        entity.id = nicknameHistory.getNicknameHistoryIdValue();
        entity.userId = nicknameHistory.getUserIdValue();
        entity.nickname = nicknameHistory.getNicknameValue();
        entity.createdAt = nicknameHistory.getCreatedAt();
        return entity;
    }

    public NicknameHistory toModel() {
        return NicknameHistory.builder()
                .id(new NicknameHistoryId(id))
                .userId(new UserId(userId))
                .nickname(new Nickname(nickname))
                .createdAt(createdAt)
                .build();
    }
}
