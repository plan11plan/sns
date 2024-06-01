package com.example.sns.presentation.post.controller.response;

import com.example.sns.core.post.domain.service.output.PostOutput;
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

    public static PostResponse from(PostOutput postOutput, UserResponse writer) {
        return PostResponse.builder()
                .id(postOutput.getId())
                .likeCount(postOutput.getLikeCount())
                .title(postOutput.getTitle())
                .content(postOutput.getContent())
                .createdAt(postOutput.getCreatedAt())
                .modifiedAt(postOutput.getModifiedAt())
                .writer(writer)
                .build();
    }
}
