package com.example.sns.core.post.domain.service.output;


import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeCountOutput {
    private final Long postId;
    private final Long likeCount;


    @Builder
    public PostLikeCountOutput(Long postId, Long likeCount) {
        this.postId = postId;
        this.likeCount = likeCount;
    }
}
