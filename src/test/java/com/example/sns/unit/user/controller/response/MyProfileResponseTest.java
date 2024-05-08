//package com.example.sns.unit.user.controller.response;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.example.sns.core.user.controller.response.MyProfileResponse;
//import com.example.sns.core.user.domain.entity.Email;
//import com.example.sns.core.user.domain.entity.Nickname;
//import com.example.sns.core.user.domain.entity.Sex;
//import com.example.sns.core.user.domain.entity.root.User;
//import java.time.LocalDate;
//import java.util.UUID;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class MyProfileResponseTest {
//
//    @DisplayName("User으로 응답을 생성할 수 있다.")
//    @Test
//    void test() {
//        User user = User.builder()
//                .email(new Email("email@gmail.com"))
//                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
//                .certificationCode(UUID.randomUUID().toString())
//                .build();
//
//        // when
//        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);
//
//        // then
//        assertThat(myProfileResponse.getEmail()).isEqualTo("email@gmail.com");
//
//    }
//
//}