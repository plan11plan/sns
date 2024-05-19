package com.example.sns.presentation.post.controller.response;

import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.presentation.user.controller.response.UserResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UserResponse writer;

    public static PostResponse from(PostDto postDto, UserResponse writer) {
        return PostResponse.builder()
                .id(postDto.getId())
                .likeCount(postDto.getLikeCount())
                .title(postDto.getTitle().getValue())
                .content(postDto.getContent().getValue())
                .createdAt(postDto.getCreatedAt())
                .modifiedAt(postDto.getModifiedAt())
                .writer(writer)
                .build();
    }
}
