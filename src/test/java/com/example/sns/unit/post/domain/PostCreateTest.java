package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCreateTest {

    @DisplayName("[생성] PostCreate로 게시물을 만들 수 있다.")
    @Test
    void create() {
        // given
        WriterId writerId = WriterId.of(1L);
        Title title = Title.of("title");
        Content content = Content.of("content");
        PostCreate postCreate = PostCreate.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
//                .status(PostStatus.PUBLISHED) 내부 위치
                .build();

        LocalDateTime now = LocalDateTime.now();

        // when
        Post post = Post.from(postCreate, now);

        // then
        assertAll(
                () -> assertThat(post.getWriterId().getValue()).isEqualTo(1L),
                () -> assertThat(post.getContent().getValue()).isEqualTo("content"),
                () -> assertThat(post.getTitle().getValue()).isEqualTo("title"),
                () -> assertThat(post.getStatusValue()).isEqualTo(PostStatus.PUBLISHED.name()),
                () -> assertThat(post.getCreatedAt()).isEqualTo(now),
                () -> assertThat(post.getModifiedAt()).isNull(),
                () -> assertThat(post.getLikeCount()).isEqualTo(0L)
        );
    }

    @DisplayName("[생성] PostCreate로 작성된 게시물의 기본 상태는 PUBLISHED이다.")
    @Test
    void createPostWithDefaultStatus() {
        // given
        WriterId writerId = WriterId.of(2L);
        Title title = Title.of("another title");
        Content content = Content.of("another content");
        PostCreate postCreate = PostCreate.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
//                .status(PostStatus.PUBLISHED) 내부 위치
                .build();

        LocalDateTime now = LocalDateTime.now();

        // when
        Post post = Post.from(postCreate, now);

        // then
        assertAll(
                () -> assertThat(post.getWriterId().getValue()).isEqualTo(2L),
                () -> assertThat(post.getContent().getValue()).isEqualTo("another content"),
                () -> assertThat(post.getTitle().getValue()).isEqualTo("another title"),
                () -> assertThat(post.getStatusValue()).isEqualTo(PostStatus.PUBLISHED.name()),
                () -> assertThat(post.getCreatedAt()).isEqualTo(now),
                () -> assertThat(post.getModifiedAt()).isNull(),
                () -> assertThat(post.getLikeCount()).isEqualTo(0L)
        );
    }
}