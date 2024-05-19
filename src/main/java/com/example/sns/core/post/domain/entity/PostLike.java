package com.example.sns.core.post.domain.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLike {
    private final Long id;
    private final Long writerId;
    private final Long postId;
    private final LocalDateTime createdAt;

    @Builder
    public PostLike(Long id, Long writerId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.writerId = writerId;
        this.postId = postId;
        this.createdAt = createdAt;
    }
}
