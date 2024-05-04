package com.example.sns.core.post.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {


    @DisplayName("[생성] 포스트를 만든다.")
    @Test
    void createPost(){
        // given
        Title title = new Title("title");
        Contents contents = new Contents("contents");

        // when
        Post post = Post.builder()
                .title(title)
                .contents(contents)
                .build();
        // Expected
        Assertions.assertThat(post.getTitle().getValue()).isEqualTo("title");
        Assertions.assertThat(post.getContents().getValue()).isEqualTo("contents");
    }
    @DisplayName("[생성 예외] 포스트를 만든다.")
    @Test
    void createPost2(){
        // given
        Title title = new Title("title");
        Contents contents = new Contents("contents");

        // when
        Post post = Post.builder()
                .title(title)
                .contents(contents)
                .build();
        // Expected
        Assertions.assertThat(post.getTitle().getValue()).isEqualTo("title");
        Assertions.assertThat(post.getContents().getValue()).isEqualTo("contents");
    }

}