package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.service.PostLikeWriteService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.output.UserOutput;
import com.example.sns.mock.post.FakePostLikeWriteRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostLikeWriteServiceTest {

    @Mock
    private TimeHolder timeHolder;

    @InjectMocks
    private PostLikeWriteService postLikeWriteService;

    private FakePostLikeWriteRepository fakePostLikeWriteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakePostLikeWriteRepository = new FakePostLikeWriteRepository();
        postLikeWriteService = new PostLikeWriteService(fakePostLikeWriteRepository, timeHolder);
    }

    @DisplayName("[저장] 게시물에 좋아요를 추가할 수 있다.")
    @Test
    void createPostLike() {
        // given
        Post post = Post.builder()
                .id(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .title(Title.of("title"))
                .content(Content.of("content"))
                .build();
        UserOutput user = UserOutput.builder()
                .id(1L)
                .build();
        LocalDateTime now = LocalDateTime.now();

        when(timeHolder.nowDateTime()).thenReturn(now);

        // when
        postLikeWriteService.create(PostOutput.from(post), user);

        // then
        assertThat(fakePostLikeWriteRepository.getAll()).hasSize(1);
        PostLike savedPostLike = fakePostLikeWriteRepository.getAll().get(0);
        assertThat(savedPostLike.getUserId().getValue()).isEqualTo(1L);
        assertThat(savedPostLike.getPostId().getValue()).isEqualTo(1L);
        assertThat(savedPostLike.getCreatedAt()).isEqualTo(now);

        verify(timeHolder, times(1)).nowDateTime();
    }
}
