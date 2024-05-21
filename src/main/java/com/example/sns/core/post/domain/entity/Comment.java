package com.example.sns.core.post.domain.entity;

import com.example.sns.core.post.domain.entity.request.CommentCreate;
import com.example.sns.core.post.domain.entity.request.CommentUpdate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {
    private final CommentId id;

    private final CommentId parentId;
    private final PostId postId;
    private final WriterId writerId;
    private final CommentContent content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public Comment(CommentId id, PostId postId, CommentId parentId, WriterId writerId, CommentContent content,
                   LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.postId = postId;
        this.parentId = parentId;
        this.writerId = writerId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Comment from(CommentCreate commentCreate, LocalDateTime createdAt) {
        return Comment.builder()
                .postId(commentCreate.getPostId())
                .parentId(commentCreate.getParentId())
                .writerId(commentCreate.getWriterId())
                .content(commentCreate.getContent())
                .createdAt(createdAt)
                .build();
    }

    public Comment update(CommentUpdate commentUpdate, LocalDateTime modifiedAt) {
        return Comment.builder()
                .id(id)
                .postId(postId)
                .parentId(parentId)
                .writerId(writerId)
                .content(commentUpdate.getContent())
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }


    public Long getPostIdValue() {
        return postId.getValue();
    }

    public String getContentValue() {
        return content.getValue();
    }

    public Long getCommentParentIdValue() {
        return parentId.getValue();
    }

    public Long getWriterIdValue() {
        return writerId.getValue();
    }

    public Long getCommentIdValue() {
        return this.id != null ? this.id.getValue() : null;
    }
}
