package com.example.sns.core.post.domain.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Timeline {
    private final Long id;
    private final Long userId;
    private final Long postId;
    private final LocalDateTime createdAt;


    @Builder
    public Timeline(Long id, Long userId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        //Id null일때만 createdAt 생성 허용.
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
