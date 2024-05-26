package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import com.example.sns.core.post.service.PostUpdateService;
import com.example.sns.mock.post.FakePostReadRepository;
import com.example.sns.mock.post.FakePostWriteRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostUpdateServiceTest {

    private FakePostWriteRepository postWriteRepository;
    private FakePostReadRepository postReadRepository;
    private PostUpdateService postUpdateService;

    @BeforeEach
    void setUp() {
        postWriteRepository = new FakePostWriteRepository();
        postReadRepository = new FakePostReadRepository();
        postUpdateService = new PostUpdateService(postWriteRepository, postReadRepository);

        // 미리 데이터를 추가합니다.
        Long postId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        WriterId writerId = WriterId.of(1L);
        Title originalTitle = Title.of("original title");
        Content originalContent = Content.of("original content");

        Post post = Post.builder()
                .id(PostId.of(postId))
                .writerId(writerId)
                .title(originalTitle)
                .content(originalContent)
                .status(PostStatus.PUBLISHED)
                .likeCount(0L)
                .createdAt(createdAt)
                .build();

        postWriteRepository.save(post);
        postReadRepository.save(post); // 읽기 리포지토리에도 추가
    }

    @DisplayName("[수정] PostUpdateService로 Post를 수정할 수 있다.")
    @Test
    void updatePost() {
        // given
        Long postId = 1L;
        LocalDateTime updatedAt = LocalDateTime.now();
        Title newTitle = Title.of("new title");
        Content newContent = Content.of("new content");

        PostUpdate postUpdate = PostUpdate.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        // when
        Post updatedPost = postUpdateService.update(postId, postUpdate);

        // then
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle().getValue()).isEqualTo("new title");
        assertThat(updatedPost.getContent().getValue()).isEqualTo("new content");
        assertThat(updatedPost.getModifiedAt()).isEqualToIgnoringNanos(updatedAt);
        assertThat(updatedPost.getStatusValue()).isEqualTo(PostStatus.UPDATED.name());
    }
}
