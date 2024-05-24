package com.example.sns.core.post.domain.entity;

import com.example.sns.core.post.domain.entity.request.PostLikeCreate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLike {
    private final PostLikeId id;
    private final UserId userId;
    private final PostId postId;
    private final LocalDateTime createdAt;

    @Builder
    public PostLike(PostLikeId id, UserId userId, PostId postId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    public static PostLike from(PostLikeCreate postLikeCreate, LocalDateTime createdAt) {
        return PostLike.builder()
                .userId(postLikeCreate.getUserId())
                .postId(postLikeCreate.getPostId())
                .createdAt(createdAt)
                .build();
    }

    public Long getPostLikeIdValue() {
        return this.id != null ? this.id.getValue() : null;
    }
    public Long getUserIdValue(){
        return userId.getValue();
    }
    public Long getPostIdValue(){
        return postId.getValue();
    }
}
