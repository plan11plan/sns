package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCreateTest {

    @DisplayName("PostCreate로 게시물을 만들 수 있다.")
    @Test
    void create(){
        // given
        LocalDateTime now = LocalDateTime.now();

        PostCreate postCreate = PostCreate.builder()
                .writerId(new WriterId(1L))
                .title(new Title("title"))
                .content(new Content("content"))
                .build();

        // when
        Post post = Post.from(postCreate,now);

        // then
        assertThat(post.getWriterId().getValue()).isEqualTo(1L);
        assertThat(post.getContent().getValue()).isEqualTo("content");
        assertThat(post.getTitle().getValue()).isEqualTo("title");
        assertThat(post.getCreatedAt()).isEqualTo(now);
        assertThat(post.getModifiedAt()).isNull();


    }


}