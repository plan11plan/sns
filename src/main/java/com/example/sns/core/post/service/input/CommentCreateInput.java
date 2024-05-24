package com.example.sns.core.post.service.input;

import com.example.sns.application.command.comment.CommentCreateCommand;
import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateInput {
    private final PostId postId;
    private final CommentId parentId;
    private final WriterId writerId;
    private final CommentContent content;

    public static CommentCreateInput from(CommentCreateCommand command) {
        return CommentCreateInput.builder()
                .postId(new PostId(command.getPostId()))
                .parentId(command.getParentId() != null ? new CommentId(command.getParentId()) : null)
                .writerId(new WriterId(command.getWriterId()))
                .content(new CommentContent(command.getContent()))
                .build();
    }
}
