//package com.example.sns.unit.post.domain;
//
//import com.example.sns.common.infrstructure.SystemClockHolder;
//import com.example.sns.common.service.port.ClockHolder;
//import com.example.sns.core.post.domain.entity.Content;
//import com.example.sns.core.post.domain.entity.Post;
//import com.example.sns.core.post.domain.entity.Title;
//import com.example.sns.core.post.domain.entity.request.PostCreate;
//import com.example.sns.core.user.domain.entity.Email;
//import com.example.sns.core.user.domain.entity.Nickname;
//import com.example.sns.core.user.domain.entity.Sex;
//import com.example.sns.core.user.domain.entity.root.User;
//import java.time.LocalDate;
//import java.util.UUID;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class PostCreateTest {
//
//    @DisplayName("PostCreate로 게시물을 만들 수 있다.")
//    @Test
//    void create(){
//        // given
//        PostCreate postCreate = PostCreate.builder()
//                .title(new Title("title"))
//                .content(new Content("content"))
//                .writerId(1L)
//                .build();
//        User writer = User.builder()
//                .email(new Email("email@gmail.com"))
//                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
//                .certificationCode(UUID.randomUUID().toString())
//                .build();
//        ClockHolder clockHolder = new SystemClockHolder();
//
//        // when
//        Post post = Post.from(writer, postCreate,clockHolder);
//
//        // then
//        Assertions.assertThat(post.getContent().getValue()).isEqualTo("content");
//
//
//    }
//
//
//}