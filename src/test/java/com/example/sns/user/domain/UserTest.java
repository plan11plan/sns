package com.example.sns.user.domain;

import com.example.sns.user.domain.entity.Sex;
import com.example.sns.user.domain.entity.UserStatus;
import com.example.sns.user.domain.entity.root.User;
import com.example.sns.user.domain.entity.vo.UserCreate;
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

        Assertions.assertThat(user.getName()).isEqualTo("name");
        Assertions.assertThat(user.getNickname()).isEqualTo("nickname");
        Assertions.assertThat(user.getEmail()).isEqualTo("email@gmail.com");
        Assertions.assertThat(user.getPassword()).isEqualTo("password");
        Assertions.assertThat(user.getAge()).isEqualTo(26);
        Assertions.assertThat(user.getSex()).isEqualTo(Sex.M);
        Assertions.assertThat(user.getUserStatus()).isEqualTo(UserStatus.ACTIVE);


    }


}