package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.post.service.PostLikeReadService;
import com.example.sns.core.post.service.output.PostLikeCountOutput;
import com.example.sns.core.post.service.output.PostLikeCountsOutput;
import com.example.sns.mock.post.FakePostLikeReadRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostLikeReadServiceTest {

    private PostLikeReadService postLikeReadService;
    private FakePostLikeReadRepository fakePostLikeReadRepository;

    @BeforeEach
    void setUp() {
        fakePostLikeReadRepository = new FakePostLikeReadRepository();
        postLikeReadService = new PostLikeReadService(fakePostLikeReadRepository);
    }

    @DisplayName("[조회] 단일 게시물의 좋아요 수를 조회할 수 있다.")
    @Test
    void getPostLike() {
        // given
        Long postId = 1L;
        Long likeCount = 5L;
        fakePostLikeReadRepository.saveLikeCount(postId, likeCount);

        // when
        PostLikeCountOutput result = postLikeReadService.getPostLike(postId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getPostId()).isEqualTo(postId);
        assertThat(result.getLikeCount()).isEqualTo(likeCount);
    }

    @DisplayName("[조회] 여러 게시물의 좋아요 수를 조회할 수 있다.")
    @Test
    void getPostLikes() {
        // given
        List<Long> postIds = List.of(1L, 2L, 3L);
        fakePostLikeReadRepository.saveLikeCount(1L, 5L);
        fakePostLikeReadRepository.saveLikeCount(2L, 10L);
        fakePostLikeReadRepository.saveLikeCount(3L, 15L);

        // when
        PostLikeCountsOutput result = postLikeReadService.getPostLikes(postIds);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getPostLikeCountsSize()).isEqualTo(3);
        assertThat(result.getPostLike(1L).getLikeCount()).isEqualTo(5L);
        assertThat(result.getPostLike(2L).getLikeCount()).isEqualTo(10L);
        assertThat(result.getPostLike(3L).getLikeCount()).isEqualTo(15L);
    }
}
