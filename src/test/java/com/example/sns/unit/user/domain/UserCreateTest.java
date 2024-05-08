package com.example.sns.unit.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.request.UserCreate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserCreateTest {

    @DisplayName("[생성] 유저의 생성 요청 객체를 만든다.")
    @Test
    void UserCreate(){
        UserCreate userCreate = UserCreate.builder()
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999,7,28)))
                .status(UserStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        assertThat(userCreate.getNickname().getValue()).isEqualTo("nickname");
        assertThat(userCreate.getEmail().getEmail()).isEqualTo("email@gmail.com");
        assertThat(userCreate.getPassword().getPassword()).isEqualTo("password");
        assertThat(userCreate.getSex().toString()).isEqualTo("M");
        assertThat(userCreate.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(userCreate.getBirthday().getBirthday()).isEqualTo("1999-07-28");

    }


}