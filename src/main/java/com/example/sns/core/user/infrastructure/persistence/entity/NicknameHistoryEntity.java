package com.example.sns.core.user.infrastructure.persistence.entity;

import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
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
    private Nickname nickname;
    private LocalDateTime createdAt;

    @Builder
    public NicknameHistoryEntity(Long id, Long userId, Nickname nickname, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public static NicknameHistoryEntity from(NicknameHistory nicknameHistory) {
        return null;
    }
    public NicknameHistory toModel(){
        return null;
    }

}
