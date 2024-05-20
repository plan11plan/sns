package com.example.sns.core.post.infrastructure.repository.entity.outputVo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeCountDaoResponse {
    private Long postId;
    private Long likeCount;


    @Builder
    public PostLikeCountDaoResponse(Long postId, Long likeCount) {
        this.postId = postId;
        this.likeCount = likeCount;
    }

}
