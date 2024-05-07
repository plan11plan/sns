package com.example.sns.unit.post.domain;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.Title;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {


    @DisplayName("[생성] 포스트를 만든다.")
    @Test
    void createPost(){
        // given
        Title title = new Title("title");
        Content content = new Content("content");

        // when
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        // Expected
        Assertions.assertThat(post.getTitle().getValue()).isEqualTo("title");
        Assertions.assertThat(post.getContent().getValue()).isEqualTo("content");
    }
    @DisplayName("[생성 예외] 포스트를 만든다.")
    @Test
    void createPost2(){
        // given
        Title title = new Title("title");
        Content content = new Content("content");

        // when
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        // Expected
        Assertions.assertThat(post.getTitle().getValue()).isEqualTo("title");
        Assertions.assertThat(post.getContent().getValue()).isEqualTo("content");
    }

    @DisplayName("PostCreate 로 게시물을 만들 수 있다.")
    @Test
    void postCreate(){

    }
    @DisplayName("update로 데이터를 수정 할 수 있다.")
    @Test
    void update(){

    }
}