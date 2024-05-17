//package com.example.sns.unit.post.service;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.example.sns.core.post.domain.entity.Content;
//import com.example.sns.core.post.domain.entity.Post;
//import com.example.sns.core.post.domain.entity.request.PostCreate;
//import com.example.sns.core.post.domain.entity.request.PostUpdate;
//import com.example.sns.core.post.service.PostReadService;
//import com.example.sns.core.user.domain.entity.Email;
//import com.example.sns.core.user.domain.entity.Nickname;
//import com.example.sns.core.user.domain.entity.Sex;
//import com.example.sns.core.user.domain.entity.UserStatus;
//import com.example.sns.core.user.domain.entity.root.User;
//import com.example.sns.mock.FakePostRepository;
//import com.example.sns.mock.FakeUserRepository;
//import com.example.sns.mock.TestClockHolder;
//import java.time.LocalDate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class PostServiceTest {
//
//    private PostCommandService postCommandService;
//    private PostReadService postReadService;
//
//    @BeforeEach
//    void init() {
//        FakePostRepository fakePostRepository = new FakePostRepository();
//        FakeUserRepository fakeUserRepository = new FakeUserRepository();
//        // DI
//        this.postCommandService = PostCommandServiceImpl.builder()
//                .postWriteRepository(fakePostRepository)
//                .userRepository(fakeUserRepository)
//                .clockHolder(new TestClockHolder(1679530673958L))
//                .build();
//        // DI
//        this.postReadService = PostReadService.builder()
//                .postWriteRepository(fakePostRepository)
//                .build();
//        // input1
//        User user1 = User.builder()
//                .id(1L)
//                .email(new Email("email@gmail.com"))
//                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
//                .address(new Address("address"))
//                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
//                .userStatus(UserStatus.ACTIVE)
//                .lastLoginAt(0L)
//                .build();
//        // input2
//        User user2 = User.builder()
//                .id(1L)
//                .email(new Email("email2@gmail.com"))
//                .userInfo(new UserInfo(new Name("name2"), new Nickname("nickname2"), Sex.W, LocalDate.now()))
//                .address(new Address("address2"))
//                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
//                .userStatus(UserStatus.ACTIVE)
//                .lastLoginAt(0L)
//                .build();
//        // save
//        fakeUserRepository.save(user1);
//        fakeUserRepository.save(user2);
//        fakePostRepository.save(Post.builder()
//                .id(1L)
//                .content(new Content("helloworld"))
//                .createdAt(1678530673958L)
//                .modifiedAt(0L)
//                .writer(user1)
//                .build());
//    }
//
//    @Test
//    void getById는_존재하는_게시물을_내려준다() {
//        // given
//        // when
//        Post result = postReadService.getById(1);
//
//        // then
//        assertThat(result.getContent()).isEqualTo("helloworld");
//        assertThat(result.getWriterId().getEmail()).isEqualTo("kok202@naver.com");
//    }
//
//    @Test
//    void postCreateDto_를_이용하여_게시물을_생성할_수_있다() {
//        // given
//        PostCreate postCreate = PostCreate.builder()
//                .writerId(1)
//                .content(new Content("foobar"))
//                .build();
//
//        // when
//        Post result = postCommandService.create(postCreate);
//
//        // then
//        assertThat(result.getId()).isNotNull();
//        assertThat(result.getContent()).isEqualTo("foobar");
//        assertThat(result.getCreatedAt()).isEqualTo(1679530673958L);
//    }
//
//    @Test
//    void postUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
//        // given
//        PostUpdate postUpdate = PostUpdate.builder()
//                .content(new Content("hello world :)"))
//                .build();
//
//        // when
//        postCommandService.update(1, postUpdate);
//
//        // then
//        Post post = postReadService.getById(1);
//        assertThat(post.getContent()).isEqualTo("hello world :)");
//        assertThat(post.getModifiedAt()).isEqualTo(1679530673958L);
//    }
//
//}
