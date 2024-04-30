package com.example.sns.user.domain.entity.vo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.sns.user.domain.entity.Sex;
import com.example.sns.user.domain.entity.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserCreateTest {

    @DisplayName("[생성] 유저의 생성 요청 객체를 만든다.")
    @Test
    void UserCreate(){
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
        Assertions.assertThat(userCreate.name()).isEqualTo("name");
        Assertions.assertThat(userCreate.nickname()).isEqualTo("nickname");
        Assertions.assertThat(userCreate.email()).isEqualTo("email@gmail.com");
        Assertions.assertThat(userCreate.password()).isEqualTo("password");
        Assertions.assertThat(userCreate.age()).isEqualTo(26);
        Assertions.assertThat(userCreate.sex()).isEqualTo(Sex.M);
        Assertions.assertThat(userCreate.userStatus()).isEqualTo(UserStatus.ACTIVE);

    }


}