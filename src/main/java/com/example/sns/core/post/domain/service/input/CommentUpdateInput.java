package com.example.sns.core.post.domain.service.input;

import com.example.sns.application.command.comment.CommentUpdateCommand;
import com.example.sns.core.post.domain.entity.CommentContent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdateInput {
    private final CommentContent content;

    public static CommentUpdateInput from(CommentUpdateCommand command) {
        return CommentUpdateInput.builder()
                .content(new CommentContent(command.getContent()))
                .build();
    }
}
