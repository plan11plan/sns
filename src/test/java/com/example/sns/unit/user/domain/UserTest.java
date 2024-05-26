package com.example.sns.unit.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.sns.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.common.service.port.UuidHolder;
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
import com.example.sns.mock.TestUuidHolder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("[생성] User는 UserCreate 객체로 생성할 수 있다.")
    @Test
    void createMember() {
        // given
        LocalDateTime createTime = LocalDateTime.now();
        UserCreate userCreate = UserCreate.builder()
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
//                .status(UserStatus.PENDING)
                .createdAt(createTime)
                .build();
        UuidHolder uuidHolder = new TestUuidHolder("1234");

        // when
        User user = User.from(userCreate, uuidHolder);

        // then
        assertThat(user.getNickname().getValue()).isEqualTo("nickname");
        assertThat(user.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(user.getPassword().getValue()).isEqualTo("password");
        assertThat(user.getSex().toString()).isEqualTo("M");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getBirthday().getValue()).isEqualTo(LocalDate.of(1999, 7, 28));
        assertThat(user.getCreatedAt()).isEqualTo(createTime);
        assertThat(user.getCertificationCode()).isEqualTo("1234");
    }

    @DisplayName("[수정] User는 UserUpdate 객체로 데이터를 수정할 수 있다.")
    @Test
    void update() {
        // given
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .id(new UserId(1L))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .nickname(new Nickname("nickname"))
                .sex(Sex.W)
                .status(UserStatus.ACTIVE)
                .certificationCode("1234")
                .createdAt(now)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .lastLoginAt(now)
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .email(new Email("changeEmail@gmail.com"))
                .password(new Password("changePassword"))
                .nickname(new Nickname("changeNickname"))
                .build();

        // when
        User updatedUser = user.update(userUpdate);

        // then
        assertThat(updatedUser.getNickname().getValue()).isEqualTo("changeNickname");
        assertThat(updatedUser.getPassword().getValue()).isEqualTo("changePassword");
        assertThat(updatedUser.getEmail().getValue()).isEqualTo("changeEmail@gmail.com");
        assertThat(updatedUser.getUserIdValue()).isEqualTo(1L);
        assertThat(updatedUser.getSex().toString()).isEqualTo("W");
        assertThat(updatedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(updatedUser.getCertificationCode()).isEqualTo("1234");
        assertThat(updatedUser.getCreatedAt()).isEqualTo(now);
        assertThat(updatedUser.getBirthday().getValue()).isEqualTo(LocalDate.of(1999, 7, 28));
    }

    @DisplayName("[로그인] User는 로그인을 할 수 있고, 로그인시 마지막 로그인 시간이 변경된다.")
    @Test
    void login() {
        // given
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .id(new UserId(1L))
                .nickname(new Nickname("nickname"))
                .email(new Email("email@gmail.com"))
                .password(new Password("password"))
                .sex(Sex.M)
                .birthday(new Birthday(LocalDate.of(1999, 7, 28)))
                .status(UserStatus.ACTIVE)
                .certificationCode("1234")
                .lastLoginAt(now)
                .createdAt(now)
                .build();

        // when
        LocalDateTime loginTime = LocalDateTime.now();
        User loggedInUser = user.login(loginTime);

        // then
        assertThat(loggedInUser.getLastLoginAt()).isEqualTo(loginTime);
        assertThat(loggedInUser.getCreatedAt()).isEqualTo(now);
        assertThat(loggedInUser.getUserIdValue()).isEqualTo(1L);
        assertThat(loggedInUser.getNickname().getValue()).isEqualTo("nickname");
        assertThat(loggedInUser.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(loggedInUser.getPassword().getValue()).isEqualTo("password");
        assertThat(loggedInUser.getSex().toString()).isEqualTo("M");
        assertThat(loggedInUser.getBirthday().getValue()).isEqualTo(LocalDate.of(1999, 7, 28));
        assertThat(loggedInUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(loggedInUser.getCertificationCode()).isEqualTo("1234");
    }

    @DisplayName("[인증] 유효한 인증 코드로 계정을 활성화할 수 있다.")
    @Test
    void certificate() {
        // given
        String certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa";
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

        // when
        User certifiedUser = user.certificate(certificationCode);

        // then
        assertThat(certifiedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(certifiedUser.getUserIdValue()).isEqualTo(1L);
        assertThat(certifiedUser.getNickname().getValue()).isEqualTo("nickname");
        assertThat(certifiedUser.getEmail().getValue()).isEqualTo("email@gmail.com");
        assertThat(certifiedUser.getPassword().getValue()).isEqualTo("password");
        assertThat(certifiedUser.getSex().toString()).isEqualTo("M");
        assertThat(certifiedUser.getBirthday().getValue()).isEqualTo(LocalDate.of(1999, 7, 28));
        assertThat(certifiedUser.getCertificationCode()).isEqualTo(certificationCode);
        assertThat(certifiedUser.getLastLoginAt()).isEqualTo(now);
        assertThat(certifiedUser.getCreatedAt()).isEqualTo(now);
    }

    @DisplayName("[인증 실패] 잘못된 인증 코드로 계정을 활성화 시도하면 에러를 던진다.")
    @Test
    void certificate_error() {
        // given
        String certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa";
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
        assertThatThrownBy(() -> user.certificate("wrong-code"))
                .isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}
