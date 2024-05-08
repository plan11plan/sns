package com.example.sns.core.post.controller.response;

import com.example.sns.core.post.domain.entity.Post;
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

    public static PostResponse from(Post post,UserResponse writer) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle().getValue())
                .content(post.getContent().getValue())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .writer(writer)
                .build();
    }
}
