package com.example.sns.core.post.service.output;

import java.util.Map;
import lombok.Getter;

@Getter
public class PostLikeCountsOutput {
    private final Map<Long, PostLikeCountOutput> postLikes;


    public PostLikeCountsOutput(Map<Long, PostLikeCountOutput> postLikes) {
        this.postLikes = postLikes;
    }

    public PostLikeCountOutput getPostLike(Long postId) {
        return postLikes.getOrDefault(postId, new PostLikeCountOutput(postId, 0L));
    }

    public boolean containsPost(Long postId) {
        return postLikes.containsKey(postId);
    }

    public int getPostLikeCountsSize() {
        return postLikes.size();
    }
}
