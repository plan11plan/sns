package com.example.sns.core.post.domain.entity.request;

import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.UserId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeCreate {
    private final UserId userId;
    private final PostId postId;


    @Builder
    public PostLikeCreate(UserId userId, PostId postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
