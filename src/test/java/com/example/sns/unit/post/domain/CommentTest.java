package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.*;
import com.example.sns.core.post.domain.entity.request.CommentCreate;
import com.example.sns.core.post.domain.entity.request.CommentUpdate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @DisplayName("[생성] Comment는 CommentCreate 객체로 생성할 수 있다.")
    @Test
    void byCommentCreate() {
        // given
        LocalDateTime createTime = LocalDateTime.now();
        PostId postId = PostId.of(1L);
        CommentId parentId = CommentId.of(1L);
        WriterId writerId = WriterId.of(1L);
        CommentContent content = CommentContent.of("content");
        CommentCreate commentCreate = CommentCreate.builder()
                .postId(postId)
                .parentId(parentId)
                .writerId(writerId)
                .content(content)
                .build();

        // when
        Comment comment = Comment.from(commentCreate, createTime);

        // then
        assertAll(
                () -> assertThat(comment.getPostId().getValue()).isEqualTo(1L),
                () -> assertThat(comment.getCommentParentIdValue()).isEqualTo(1L),
                () -> assertThat(comment.getWriterIdValue()).isEqualTo(1L),
                () -> assertThat(comment.getContent().getValue()).isEqualTo("content"),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(createTime),
                () -> assertThat(comment.getModifiedAt()).isNull()
        );
    }

    @DisplayName("[수정] Comment는 CommentUpdate 객체로 수정할 수 있다.")
    @Test
    void byCommentUpdate() {
        // given
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        CommentId commentId = CommentId.of(1L);
        PostId postId = PostId.of(1L);
        CommentId parentId = CommentId.of(1L);
        WriterId writerId = WriterId.of(1L);
        CommentContent originalContent = CommentContent.of("original content");

        Comment comment = Comment.builder()
                .id(commentId)
                .postId(postId)
                .parentId(parentId)
                .writerId(writerId)
                .content(originalContent)
                .createdAt(createdAt)
                .build();

        CommentContent newContent = CommentContent.of("new content");
        CommentUpdate commentUpdate = CommentUpdate.builder()
                .content(newContent)
                .build();

        // when
        Comment updatedComment = comment.update(commentUpdate, updatedAt);

        // then
        assertAll(
                () -> assertThat(updatedComment.getCommentIdValue()).isEqualTo(1L),
                () -> assertThat(updatedComment.getPostIdValue()).isEqualTo(1L),
                () -> assertThat(updatedComment.getCommentParentIdValue()).isEqualTo(1L),
                () -> assertThat(updatedComment.getWriterIdValue()).isEqualTo(1L),
                () -> assertThat(updatedComment.getContentValue()).isEqualTo("new content"),
                () -> assertThat(updatedComment.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(updatedComment.getModifiedAt()).isEqualTo(updatedAt)
        );
    }
}
