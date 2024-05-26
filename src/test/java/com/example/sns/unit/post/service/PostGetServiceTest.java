package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.service.PostGetService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.mock.post.FakePostReadRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostGetServiceTest {

    private PostGetService postGetService;
    private FakePostReadRepository fakePostReadRepository;

    @BeforeEach
    void setUp() {
        fakePostReadRepository = new FakePostReadRepository();
        postGetService = new PostGetService(fakePostReadRepository);
    }

    @DisplayName("[조회] 존재하는 게시물을 ID로 조회할 수 있다.")
    @Test
    void getPostById() {
        // given
        WriterId writerId = WriterId.of(1L);
        Title title = Title.of("Existing Title");
        Content content = Content.of("Existing Content");
        Post post = Post.builder()
                .id(PostId.of(1L))
                .writerId(writerId)
                .title(title)
                .content(content)
                .status(PostStatus.PUBLISHED)
                .createdAt(LocalDateTime.now())
                .build();

        fakePostReadRepository.save(post);

        // when
        PostOutput result = postGetService.getById(1L);

        // then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getWriterId()).isEqualTo(1L),
                () -> assertThat(result.getTitle()).isEqualTo("Existing Title"),
                () -> assertThat(result.getContent()).isEqualTo("Existing Content")
        );
    }

    @DisplayName("[조회] 존재하지 않는 게시물을 ID로 조회할 때 예외가 발생한다.")
    @Test
    void getPostById_NotFound() {
        // given
        Long nonExistentId = 999L;

        // when & then
        assertThatThrownBy(() -> postGetService.getById(nonExistentId))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
