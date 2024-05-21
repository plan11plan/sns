package com.example.sns.application.command.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateCommand {
    private Long postId;
    private Long parentId;
    private Long writerId;
    private String content;
}