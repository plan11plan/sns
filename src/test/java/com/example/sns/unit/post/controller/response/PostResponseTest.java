//package com.example.sns.unit.post.controller.response;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.example.sns.core.post.controller.response.PostResponse;
//import com.example.sns.core.post.domain.entity.Content;
//import com.example.sns.core.post.domain.entity.Post;
//import com.example.sns.core.post.domain.entity.Title;
//import com.example.sns.core.user.domain.entity.Email;
//import com.example.sns.core.user.domain.entity.Nickname;
//import com.example.sns.core.user.domain.entity.Sex;
//import com.example.sns.core.user.domain.entity.root.User;
//import java.time.LocalDate;
//import java.util.UUID;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class PostResponseTest {
//
//    @DisplayName("PostResponse로 응답을 생성할 수 있다.")
//    @Test
//    void response(){
//        // given
//
//        Post post = Post.builder()
//                .content(new Content("content"))
//                .title(new Title("title"))
//                .writer(User.builder()
//                        .email(new Email("email@gmail.com"))
//                        .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
//                        .certificationCode(UUID.randomUUID().toString())
//                        .build())
//                .build();
//
//        // when
//        PostResponse postResponse = PostResponse.from(post);
//
//        // then
//        assertThat(postResponse.getContent()).isEqualTo("content");
//        assertThat(postResponse.getWriter().getEmail()).isEqualTo("email@gmail.com");
//
//    }
//}