package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.service.PostCreateService;
import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.mock.post.FakePostWriteRepository;
import com.example.sns.mock.TestTimeHolder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCreateServiceTest {

    private PostCreateService postCreateService;
    private FakePostWriteRepository fakePostWriteRepository;
    private TestTimeHolder testTimeHolder;

    @BeforeEach
    void setUp() {
        fakePostWriteRepository = new FakePostWriteRepository();
        testTimeHolder = new TestTimeHolder(LocalDateTime.of(2023, 1, 1, 0, 0));
        postCreateService = PostCreateService.builder()
                .postWriteRepository(fakePostWriteRepository)
                .timeHolder(testTimeHolder)
                .build();
    }

    @DisplayName("[생성] PostCreate로 게시물을 생성할 수 있다.")
    @Test
    void createPost() {
        // given
        WriterId writerId = WriterId.of(1L);
        Title title = Title.of("New Title");
        Content content = Content.of("New Content");
        PostCreate postCreate = PostCreate.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
//                .status(PostStatus.PUBLISHED)
                .build();

        // when
        PostOutput result = postCreateService.create(postCreate);

        // then
        Post savedPost = fakePostWriteRepository.getById(result.getId());
        assertAll(
                () -> assertThat(savedPost).isNotNull(),
                () -> assertThat(savedPost.getWriterId().getValue()).isEqualTo(1L),
                () -> assertThat(savedPost.getTitle().getValue()).isEqualTo("New Title"),
                () -> assertThat(savedPost.getContent().getValue()).isEqualTo("New Content"),
                () -> assertThat(savedPost.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 1, 0, 0))
        );
    }
}