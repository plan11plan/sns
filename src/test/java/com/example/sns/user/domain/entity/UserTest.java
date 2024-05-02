package com.example.sns.user.domain.entity;

import com.example.sns.user.domain.entity.root.User;
import com.example.sns.user.domain.request.UserCreate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("[생성] UserCreate로 회원 만들기")
    @Test
    void createMember(){
        UserCreate userCreate = UserCreate.builder()
                .name("name")
                .nickname("nickname")
                .email("email@gmail.com")
                .password("password")
                .age(26)
                .sex(Sex.M)
                .userStatus(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .birthday(LocalDate.of(1999,8,28))
                .build();
        User user = User.from(userCreate);

        Assertions.assertThat(user.getName().getName()).isEqualTo("name");
        Assertions.assertThat(user.getNickname().getValue()).isEqualTo("nickname");
        Assertions.assertThat(user.getEmail().getEmail()).isEqualTo("email@gmail.com");
        Assertions.assertThat(user.getPassword().getValue()).isEqualTo("password");
        Assertions.assertThat(user.getAge().getAge()).isEqualTo(26);
        Assertions.assertThat(user.getSex()).isEqualTo(Sex.M);
        Assertions.assertThat(user.getUserStatus()).isEqualTo(UserStatus.ACTIVE);


    }


}