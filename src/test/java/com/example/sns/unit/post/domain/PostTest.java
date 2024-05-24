package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostTest {

    @DisplayName("[생성] Post는 PostCreate 객체로 생성할 수 있다.")
    @Test
    void byPostCreate() {
        // given
        LocalDateTime createTime = LocalDateTime.now();
        WriterId writerId = WriterId.of(1L);
        Title title = Title.of("title");
        Content content = Content.of("content");
        PostCreate postCreate = PostCreate.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
//                .status(PostStatus.PUBLISHED) 내부 위치
                .build();

        // when
        Post post = Post.from(postCreate, createTime);

        // then
        assertAll(
                () -> assertThat(post.getWriterId().getValue()).isEqualTo(1L),
                () -> assertThat(post.getTitle().getValue()).isEqualTo("title"),
                () -> assertThat(post.getContent().getValue()).isEqualTo("content"),
                () -> assertThat(post.getStatusValue()).isEqualTo(PostStatus.PUBLISHED.name()),
                () -> assertThat(post.getCreatedAt()).isEqualTo(createTime),
                () -> assertThat(post.getLikeCount()).isEqualTo(0L)
        );
    }

    @DisplayName("[수정] Post는 PostUpdate 객체로 수정할 수 있다.")
    @Test
    void byPostUpdate() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .title(Title.of("new title"))
                .content(Content.of("new content"))
                .build();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Post post = Post.builder()
                .id(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .title(Title.of("title"))
                .content(Content.of("content"))
//                .status(PostStatus.PUBLISHED) 내부 위치
                .likeCount(0L)
                .createdAt(createdAt)
                .build();

        // when
        Post updatedPost = post.update(postUpdate, updatedAt);

        // then
        assertAll(
                () -> assertThat(updatedPost.getWriterIdValue()).isEqualTo(1L),
                () -> assertThat(updatedPost.getTitleValue()).isEqualTo("new title"),
                () -> assertThat(updatedPost.getContentValue()).isEqualTo("new content"),
                () -> assertThat(updatedPost.getStatusValue()).isEqualTo(PostStatus.UPDATED.name()),
                () -> assertThat(updatedPost.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(updatedPost.getModifiedAt()).isEqualTo(updatedAt)
        );
    }
}