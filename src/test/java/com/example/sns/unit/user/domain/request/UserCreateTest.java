package com.example.sns.unit.user.domain.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.common.infrstructure.SystemUuidHolder;
import com.example.sns.core.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserCreate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserCreateTest {

    @DisplayName("[생성] 유저 생성 요청 객체로 유저를 만든다.")
    @Test
    void UserCreate() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .status(UserStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        UuidHolder uuidHolder = new SystemUuidHolder();
        // when

        User user = User.from(userCreate,uuidHolder);

        // then
        assertAll(
                () -> assertThat(user.getNickname().getValue()).isEqualTo("nickname"),
                () -> assertThat(user.getEmail().getValue()).isEqualTo("email@gmail.com"),
                () -> assertThat(user.getPassword().getValue()).isEqualTo("password"),
                () -> assertThat(user.getSex().toString()).isEqualTo("M"),
                () -> assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING),
                () -> assertThat(user.getBirthday().getValue()).isEqualTo("1999-07-28"),
                () -> assertThat(user.getLastLoginAt()).isNull()
        );
    }
}
