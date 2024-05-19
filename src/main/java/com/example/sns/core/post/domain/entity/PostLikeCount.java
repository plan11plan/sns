package com.example.sns.core.post.domain.entity;

import lombok.Getter;

public class PostLikeCount {
    private PostId postId;
    private LikeCount likeCount;

    public Long getPostIdValue() {
        return postId.getId();
    }

    public Long getLikeCountValue() {
        return likeCount.value;
    }


    @Getter
    public class LikeCount{
        Long value;

    }
}
