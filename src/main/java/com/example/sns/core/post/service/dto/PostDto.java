package com.example.sns.core.post.service.dto;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
    private final PostId id;
    private final WriterId writerId;
    private final Title title;
    private final Content content;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public static PostDto from(Post post, Long likeCount) {
        return PostDto.builder()
                .id(post.getId())
                .writerId(post.getWriterId())
                .title(post.getTitle())
                .likeCount(likeCount)
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();

    }

    public static PostDto toPostDto(Post post, Map<Long, Long> postLikes) {
        Long postLikeCount = postLikes.get(post.getId());
        return PostDto.builder()
                .id(post.getId())
                .writerId(post.getWriterId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(postLikeCount)
                .modifiedAt(post.getModifiedAt())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
