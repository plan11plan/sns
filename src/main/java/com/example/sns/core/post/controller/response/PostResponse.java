package com.example.sns.core.post.controller.response;

import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.user.controller.response.UserResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UserResponse writer;

    public static PostResponse from(PostDto postDto, UserResponse writer) {
        return PostResponse.builder()
                .id(postDto.getId())
                .title(postDto.getTitle().getValue())
                .content(postDto.getContent().getValue())
                .createdAt(postDto.getCreatedAt())
                .modifiedAt(postDto.getModifiedAt())
                .writer(writer)
                .build();
    }
}
