package com.example.sns.core.post.domain.entity.request;

import com.example.sns.application.command.comment.CommentUpdateCommand;
import com.example.sns.core.post.domain.entity.CommentContent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdate {
    private final CommentContent content;

    public static CommentUpdate from(CommentUpdateCommand command) {
        return CommentUpdate.builder()
                .content(new CommentContent(command.getContent()))
                .build();
    }
}