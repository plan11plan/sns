package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostUpdateTest {

    @DisplayName("[생성] PostUpdate 객체를 올바르게 생성할 수 있다.")
    @Test
    void createPostUpdate() {
        // given
        Title title = Title.of("new title");
        Content content = Content.of("new content");

        // when
        PostUpdate postUpdate = PostUpdate.builder()
                .title(title)
                .content(content)
                .build();

        // then
        assertAll(
                () -> assertThat(postUpdate.getTitle().getValue()).isEqualTo("new title"),
                () -> assertThat(postUpdate.getContent().getValue()).isEqualTo("new content"),
                () -> assertThat(postUpdate.getStatus()).isEqualTo(PostStatus.UPDATED)
        );
    }

    @DisplayName("[수정] PostUpdate로 Post를 수정할 수 있다.")
    @Test
    void updatePost() {
        // given
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        WriterId writerId = WriterId.of(1L);
        Title originalTitle = Title.of("original title");
        Content originalContent = Content.of("original content");

        Post post = Post.builder()
                .id(PostId.of(1L))
                .writerId(writerId)
                .title(originalTitle)
                .content(originalContent)
                .status(PostStatus.PUBLISHED)
                .likeCount(0L)
                .createdAt(createdAt)
                .build();

        // 새로운 PostUpdate 객체 생성
        Title newTitle = Title.of("new title");
        Content newContent = Content.of("new content");

        PostUpdate postUpdate = PostUpdate.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        // when
        Post updatedPost = post.update(postUpdate, updatedAt);

        // then
        assertAll(
                () -> assertThat(updatedPost.getTitle().getValue()).isEqualTo("new title"),
                () -> assertThat(updatedPost.getContent().getValue()).isEqualTo("new content"),
                () -> assertThat(updatedPost.getModifiedAt()).isEqualTo(updatedAt),
                () -> assertThat(updatedPost.getStatusValue()).isEqualTo(PostStatus.UPDATED.name())
        );
    }
}