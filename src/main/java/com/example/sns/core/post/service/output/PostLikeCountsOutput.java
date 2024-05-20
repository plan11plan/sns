package com.example.sns.core.post.service.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PostLikeCountsOutput {
    private final Map<Long, PostLikeCountOutput> postLikes;

    public PostLikeCountsOutput(List<PostLikeCountOutput> postLikeCountList) {
        this.postLikes = postLikeCountList.stream()
                .collect(Collectors.toMap(PostLikeCountOutput::getPostId, likeCount -> likeCount));
    }

    public PostLikeCountOutput getPostLike(Long postId) {
        return postLikes.getOrDefault(postId, new PostLikeCountOutput(postId, 0L));
    }

    public boolean containsPost(Long postId) {
        return postLikes.containsKey(postId);
    }
}
