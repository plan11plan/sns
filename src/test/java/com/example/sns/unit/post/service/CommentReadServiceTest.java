package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.service.CommentNotFoundException;
import com.example.sns.core.post.service.CommentReadService;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.mock.post.FakeCommentReadRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentReadServiceTest {

    private CommentReadService commentReadService;
    private FakeCommentReadRepository fakeCommentReadRepository;

    @BeforeEach
    void setUp() {
        fakeCommentReadRepository = new FakeCommentReadRepository();
        commentReadService = new CommentReadService(fakeCommentReadRepository);
    }

    @DisplayName("[조회] 존재하는 댓글을 ID로 조회할 수 있다.")
    @Test
    void getById() {
        // given
        Comment comment = Comment.builder()
                .id(CommentId.of(1L))
                .postId(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("content"))
                .build();
        fakeCommentReadRepository.save(comment);

        // when
        CommentOutput result = commentReadService.getById(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCommetId()).isEqualTo(1L);
        assertThat(result.getPostId()).isEqualTo(1L);
        assertThat(result.getWriterId()).isEqualTo(1L);
        assertThat(result.getContent()).isEqualTo("content");
    }

    @DisplayName("[조회] 존재하지 않는 댓글을 ID로 조회할 때 예외가 발생한다.")
    @Test
    void getById_NotFound() {
        // given
        Long nonExistentId = 999L;

        // when & then
        assertThatThrownBy(() -> commentReadService.getById(nonExistentId))
                .isInstanceOf(CommentNotFoundException.class);
//                .hasMessageContaining("Comment with id " + nonExistentId + " not found");
    }

    @DisplayName("[조회] 게시물 ID로 댓글을 조회할 수 있다.")
    @Test
    void getByPostId() {
        // given
        Comment comment = Comment.builder()
                .id(CommentId.of(1L))
                .postId(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("content"))
                .build();
        fakeCommentReadRepository.save(comment);

        // when
        List<CommentOutput> result = commentReadService.getByPostId(1L);

        // then
        assertThat(result).hasSize(1);
        CommentOutput commentOutput = result.get(0);
        assertThat(commentOutput.getCommetId()).isEqualTo(1L);
        assertThat(commentOutput.getPostId()).isEqualTo(1L);
        assertThat(commentOutput.getWriterId()).isEqualTo(1L);
        assertThat(commentOutput.getContent()).isEqualTo("content");
    }

    @DisplayName("[조회] 부모 댓글 ID로 답글을 조회할 수 있다.")
    @Test
    void getReplies() {
        // given
        Comment comment = Comment.builder()
                .id(CommentId.of(2L))
                .postId(PostId.of(1L))
                .parentId(CommentId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("reply content"))
                .build();
        fakeCommentReadRepository.save(comment);

        // when
        List<CommentOutput> result = commentReadService.getReplies(1L);

        // then
        assertThat(result).hasSize(1);
        CommentOutput commentOutput = result.get(0);
        assertThat(commentOutput.getCommetId()).isEqualTo(2L);
        assertThat(commentOutput.getPostId()).isEqualTo(1L);
        assertThat(commentOutput.getParentId()).isEqualTo(1L);
        assertThat(commentOutput.getWriterId()).isEqualTo(1L);
        assertThat(commentOutput.getContent()).isEqualTo("reply content");
    }
}
