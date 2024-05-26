package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.service.CommentWriteService;
import com.example.sns.core.post.service.input.CommentCreateInput;
import com.example.sns.core.post.service.input.CommentDeleteInput;
import com.example.sns.core.post.service.input.CommentUpdateInput;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.core.user.service.output.UserOutput;
import com.example.sns.mock.post.FakeCommentReadRepository;
import com.example.sns.mock.post.FakeCommentWriteRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

class CommentWriteServiceTest {

    @Mock
    private TimeHolder timeHolder;

    private CommentWriteService commentWriteService;

    private FakeCommentWriteRepository fakeCommentWriteRepository;
    private FakeCommentReadRepository fakeCommentReadRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeCommentWriteRepository = new FakeCommentWriteRepository();
        fakeCommentReadRepository = new FakeCommentReadRepository();
        commentWriteService = new CommentWriteService(fakeCommentWriteRepository, fakeCommentReadRepository, timeHolder);
    }

    @DisplayName("[생성] 댓글을 생성할 수 있다.")
    @Test
    void createComment() {
        // given
        LocalDateTime now = LocalDateTime.now();
        CommentCreateInput input = CommentCreateInput.builder()
                .postId(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("new comment"))
                .build();
        when(timeHolder.nowDateTime()).thenReturn(now);

        // when
        CommentOutput result = commentWriteService.create(input);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getPostId()).isEqualTo(1L);
        assertThat(result.getWriterId()).isEqualTo(1L);
        assertThat(result.getContent()).isEqualTo("new comment");
        assertThat(result.getCommetId()).isNotNull();
        assertThat(fakeCommentWriteRepository.getAll()).hasSize(1);
    }

    @DisplayName("[수정] 댓글을 수정할 수 있다.")
    @Test
    @Transactional
    void updateComment() {
        // given
        LocalDateTime now = LocalDateTime.now();
        CommentUpdateInput input = CommentUpdateInput.builder()
                .content(CommentContent.of("updated comment"))
                .build();
        UserOutput userOutput = UserOutput.builder()
                .id(1L)
                .build();
        Comment comment = Comment.builder()
                .id(CommentId.of(1L))
                .postId(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("original comment"))
                .createdAt(now)
                .build();
        fakeCommentReadRepository.save(comment);
        when(timeHolder.nowDateTime()).thenReturn(now);

        // when
        CommentOutput result = commentWriteService.update(input, userOutput);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("updated comment");
        assertThat(fakeCommentWriteRepository.getAll()).hasSize(1);
        Comment updatedComment = fakeCommentWriteRepository.getAll().get(0);
        assertThat(updatedComment.getContent().getValue()).isEqualTo("updated comment");
    }

    @DisplayName("[삭제] 댓글을 삭제할 수 있다.")
    @Test
    void deleteComment() {
        // given
        CommentDeleteInput input = CommentDeleteInput.builder()
                .id(1L)
                .build();
        Comment comment = Comment.builder()
                .id(CommentId.of(1L))
                .postId(PostId.of(1L))
                .writerId(WriterId.of(1L))
                .content(CommentContent.of("content"))
                .build();
        fakeCommentReadRepository.save(comment);

        // when
        commentWriteService.delete(input);

        // then
        assertThat(fakeCommentWriteRepository.getAll()).isEmpty();
    }
}
