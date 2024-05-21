package com.example.sns.core.post.infrastructure.repository.entity;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private Long writerId;
    private Long parentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CommentEntity from(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.id= comment.getCommentIdValue();
        entity.postId = comment.getPostIdValue();
        entity.writerId = comment.getWriterIdValue();
        entity.parentId = comment.getCommentParentIdValue();
        entity.content = comment.getContentValue();
        entity.createdAt = comment.getCreatedAt();
        entity.modifiedAt = comment.getModifiedAt();
        return entity;
    }

    public Comment toModel() {
        return Comment.builder()
                .id(new CommentId(id))
                .postId(new PostId(postId))
                .writerId(new WriterId(writerId))
                .parentId(parentId != null ? new CommentId(parentId) : null)
                .content(new CommentContent(content))
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
