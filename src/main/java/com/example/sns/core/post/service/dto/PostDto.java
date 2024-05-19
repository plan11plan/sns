package com.example.sns.core.post.service.dto;

import com.example.sns.core.post.domain.entity.Post;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
    private final Long id;
    private final Long writerId;
    private final String title;
    private final String content;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public static PostDto from(Post post, Long likeCount) {
        return PostDto.builder()
                .id(post.getPostIdValue())
                .writerId(post.getWriterIdValue())
                .title(post.getTitleValue())
                .likeCount(likeCount)
                .content(post.getContentValue())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();

    }

    public static PostDto toPostDto(Post post, Map<Long, Long> postLikes) {
        Long postLikeCount = postLikes.get(post.getId());
        return PostDto.builder()
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
