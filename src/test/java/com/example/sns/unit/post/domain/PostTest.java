package com.example.sns.unit.post.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class PostTest {

    @Test
    public void PostCreate으로_게시물을_만들_수_있다() {
        // given
        LocalDateTime createTime = LocalDateTime.now();
        PostCreate postCreate = PostCreate.builder()
                .writerId(1L)
                .title("title")
                .content("content")
                .build();
        // when
        Post post = Post.from(postCreate, createTime);

        // then
        assertThat(post.getWriterId().getValue()).isEqualTo(1L);
        assertThat(post.getTitle().getValue()).isEqualTo("title");
        assertThat(post.getContent().getValue()).isEqualTo("content");
        assertThat(post.getCreatedAt()).isEqualTo(createTime);

    }
    @Test
    public void PostUpdate로_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .title(new Title("title"))
                .content(new Content("content"))
                .build();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Post post = Post.builder()
                .id(new PostId(1L))
                .writerId(new WriterId(1L))
                .title(new Title("title"))
                .content(new Content("content"))
                .createdAt(createdAt)
                .build();

        // when
        post = post.update(postUpdate, updatedAt);

        // then
        assertThat(post.getWriterId().getValue()).isEqualTo(1L);
        assertThat(post.getTitle().getValue()).isEqualTo("title");
        assertThat(post.getContent().getValue()).isEqualTo("content");
        assertThat(post.getCreatedAt()).isEqualTo(createdAt);
        assertThat(post.getModifiedAt()).isEqualTo(updatedAt);
    }
}
