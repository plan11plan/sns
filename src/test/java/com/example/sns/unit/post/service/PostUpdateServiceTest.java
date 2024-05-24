package com.example.sns.core.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.sns.core.post.domain.entity.*;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import com.example.sns.core.post.service.port.PostWriteRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostUpdateServiceTest {

    @Mock
    private PostWriteRepository postWriteRepository;

    @InjectMocks
    private PostUpdateService postUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("[수정] PostUpdateService로 Post를 수정할 수 있다.")
    @Test
    void updatePost() {
        // given
        Long postId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
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

        Title newTitle = Title.of("new title");
        Content newContent = Content.of("new content");

        PostUpdate postUpdate = PostUpdate.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        when(postWriteRepository.getById(postId)).thenReturn(post);
        when(postWriteRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Post updatedPost = postUpdateService.update(postId, postUpdate);

        // then
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle().getValue()).isEqualTo("new title");
        assertThat(updatedPost.getContent().getValue()).isEqualTo("new content");
        assertThat(updatedPost.getModifiedAt()).isEqualToIgnoringNanos(updatedAt);
        assertThat(updatedPost.getStatusValue()).isEqualTo(PostStatus.UPDATED.name());

        verify(postWriteRepository).getById(postId);
        verify(postWriteRepository).save(any(Post.class));
    }
}
