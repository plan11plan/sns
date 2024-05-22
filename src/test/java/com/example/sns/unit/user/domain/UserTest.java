package com.example.sns.unit.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.core.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.mock.TestClockHolder;
import com.example.sns.mock.TestUuidHolder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("User는 UserCreate 객체로 생성할 수 있다.")
    @Test
    void createMember() {
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
        UuidHolder uuidHolder = new TestUuidHolder("1234");

        // when
        User user = User.from(userCreate, uuidHolder);

        // then
        assertThat(userCreate.getNickname().getValue()).isEqualTo("nickname");
        assertThat(userCreate.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(userCreate.getPassword().getValue()).isEqualTo("password");
        assertThat(userCreate.getSex().toString()).isEqualTo("M");
        assertThat(userCreate.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(userCreate.getBirthday().getValue()).isEqualTo("1999-07-28");


    }

    //TODO : 수정할게 많음.
    @DisplayName("User는 UserUpdate 객체로 데이터를 수정할 수 있다.")
    @Test
    void update() {
        // given
        String certificationCode = new TestUuidHolder("1234").random();
        long millis = new TestClockHolder(1000L).millis();
        LocalDateTime now = LocalDateTime.now();
        //
        User user = User.builder()
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .nickname(new Nickname("nickname"))
                ///
                .id(new UserId(1L))
                .sex(Sex.W)
                .status(UserStatus.ACTIVE)
                .certificationCode(certificationCode)
                .createdAt(now)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .nickname(new Nickname("nickname"))
                .lastLoginAt(now)
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .email(new Email("changeEmail@gmail.com"))
                .password(new Password("changePassword"))
                .nickname(new Nickname("changeNickname"))
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getNickname().getValue()).isEqualTo("changeNickname");
        assertThat(user.getPassword().getValue()).isEqualTo("changePassword");
        assertThat(user.getEmail().getValue()).isEqualTo("changeEmail@gmail.com");
        // and
        assertThat(user.getUserIdValue()).isEqualTo(1L);
        assertThat(user.getSex().toString()).isEqualTo("W");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getCertificationCode()).isEqualTo(certificationCode);
        assertThat(user.getCreatedAt()).isEqualTo(now);
        assertThat(user.getBirthday().getValue()).isEqualTo("1999-07-28");
    }

    @DisplayName("User는 로그인을 할 수 있고, 로그인시 마지막 로그인 시간이 변경된다.")
    @Test
    void login() {
        // given
        String certificationCode = new TestUuidHolder("1234").random();
        LocalDateTime now = LocalDateTime.now();
        //
        User user = User.builder()
                .id(new UserId(1L))
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .status(UserStatus.ACTIVE)
                .certificationCode(certificationCode)
                .lastLoginAt(now)
                .createdAt(now)
                .build();
        // when
        LocalDateTime loginTime = LocalDateTime.now();
        user = user.login(loginTime);
        // then
        assertThat(user.getUserIdValue()).isEqualTo(1L);
        assertThat(user.getNickname().getValue()).isEqualTo("nickname");
        assertThat(user.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(user.getPassword().getValue()).isEqualTo("password");
        assertThat(user.getSex().toString()).isEqualTo("M");
        assertThat(user.getBirthday().getValue()).isEqualTo("1999-07-28");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getCertificationCode()).isEqualTo(certificationCode);
        assertThat(user.getLastLoginAt()).isEqualTo(loginTime);
        assertThat(user.getCreatedAt()).isEqualTo(now);

    }

    @DisplayName("유효한 인증 코드로 계정을 활성화 할 수 있다.")
    @Test
    void certificate() {
        // given
        String certificationCode = new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa").random();
        LocalDateTime now = LocalDateTime.now();
        //
        User user = User.builder()
                .id(new UserId(1L))
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .status(UserStatus.PENDING)
                .certificationCode(certificationCode)
                .lastLoginAt(now)
                .createdAt(now)
                .build();

        // when
        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        // and
        assertThat(user.getUserIdValue()).isEqualTo(1L);
        assertThat(user.getNickname().getValue()).isEqualTo("nickname");
        assertThat(user.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(user.getPassword().getValue()).isEqualTo("password");
        assertThat(user.getSex().toString()).isEqualTo("M");
        assertThat(user.getBirthday().getValue()).isEqualTo("1999-07-28");
        assertThat(user.getCertificationCode()).isEqualTo(certificationCode);
        assertThat(user.getLastLoginAt()).isEqualTo(now);
        assertThat(user.getCreatedAt()).isEqualTo(now);
    }

    @DisplayName("User는 잘못된 인증 코드로 계정을 활성화 시도하면 에러를 던진다.")
    @Test
    void certificate_error() {
        // given
        String certificationCode = new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa").random();
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(new UserId(1L))
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .status(UserStatus.PENDING)
                .certificationCode(certificationCode)
                .lastLoginAt(now)
                .createdAt(now)
                .build();
        // expected
        Assertions.assertThatThrownBy(() -> {
            user.certificate("boo");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);


    }


}