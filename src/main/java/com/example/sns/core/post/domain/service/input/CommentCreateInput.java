package com.example.sns.core.post.domain.service.input;

import com.example.sns.application.command.comment.CommentCreateCommand;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateInput {
    private final Long postId;
    private final Long parentId;
    private final Long writerId;
    private final String content;

    public static CommentCreateInput from(CommentCreateCommand command) {
        return CommentCreateInput.builder()
                .postId(command.getPostId())
                .parentId(command.getParentId() != null ? command.getParentId() : null)
                .writerId(command.getWriterId())
                .content(command.getContent())
                .build();
    }
}
