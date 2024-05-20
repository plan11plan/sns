package com.example.sns.core.post.service.output;

import com.example.sns.core.post.domain.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostOutput {
    private final Long id;
    private final Long writerId;
    private final String title;
    private final String content;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static PostOutput from(Post post, Long likeCount) {
        return PostOutput.builder()
                .id(post.getPostIdValue())
                .writerId(post.getWriterIdValue())
                .title(post.getTitleValue())
                .likeCount(likeCount)
                .content(post.getContentValue())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public static PostOutput toPostDto(Post post, PostLikeCountsOutput postLikes) {
        Long postLikeCount = postLikes.getPostLike(post.getPostIdValue()).getLikeCount();
        return PostOutput.builder()
                .id(post.getPostIdValue())
                .writerId(post.getWriterIdValue())
                .title(post.getTitleValue())
                .content(post.getContentValue())
                .likeCount(postLikeCount)
                .modifiedAt(post.getModifiedAt())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
