package com.example.sns.core.post.domain.service.output;

import com.example.sns.core.post.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CommentOutput {
    private final Long commetId;
    private final Long postId;
    private final Long parentId;
    private final Long writerId;
    private final String content;
    private final Long likeCount;

    public static CommentOutput from(Comment comment) {
        return CommentOutput.builder()
                .commetId(comment.getCommentIdValue())
                .postId(comment.getPostIdValue())
                .parentId(comment.getParentId() != null ? comment.getCommentParentIdValue() : null)
                .writerId(comment.getWriterId().getValue())
                .content(comment.getContent().getValue())
                .build();
    }

    public static CommentOutput from(Comment comment,Long likeCount) {
        return CommentOutput.builder()
                .commetId(comment.getCommentIdValue())
                .postId(comment.getPostIdValue())
                .parentId(comment.getParentId() != null ? comment.getCommentParentIdValue() : null)
                .writerId(comment.getWriterId().getValue())
                .content(comment.getContent().getValue())
                .likeCount(likeCount)
                .build();
    }
}