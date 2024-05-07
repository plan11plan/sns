package com.example.sns.unit.user.domain;

import com.example.sns.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.common.service.port.ClockHolder;
import com.example.sns.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
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
    void createMember(){
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("email@gmail.com")
                .nickname("nickname")
                .password("password")
                .userStatus(UserStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        UuidHolder uuidHolder = new TestUuidHolder("1234");

        // when
        User user = User.from(userCreate,uuidHolder);

        // then
        Assertions.assertThat(user.getEmail().getEmail()).isEqualTo("email@gmail.com");
        Assertions.assertThat(user.getPassword().getValue()).isEqualTo("password");
        Assertions.assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        Assertions.assertThat(user.getCertificationCode()).isEqualTo("1234");


    }
    //TODO : 수정할게 많음.
    @DisplayName("User는 UserUpdate 객체로 데이터를 수정할 수 있다.")
    @Test
    void update(){
        // given
        User user = User.builder()
                .id(1L)
                .email(new Email("email@gmail.com"))
                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
                .userStatus(UserStatus.ACTIVE)
                .password(new Password("password"))
                .certificationCode(new TestUuidHolder("1234").random())
                .lastLoginAt(new TestClockHolder(1000L).millis())
                .build();
        UuidHolder uuidHolder = new TestUuidHolder("1234");

        UserUpdate userUpdate = UserUpdate.builder()
                .email("changeEmail@gmail.com")
                .password("changePassword")
                .build();

        // when
        user  = user.update(userUpdate);

        // then
        Assertions.assertThat(user.getEmail().getEmail()).isEqualTo("changeEmail@gmail.com");
        Assertions.assertThat(user.getUserInfo().getNickname().getValue()).isEqualTo("nickname");
        Assertions.assertThat(user.getPassword().getPassword()).isEqualTo("changePassword");
        Assertions.assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        Assertions.assertThat(user.getCertificationCode()).isEqualTo("1234");
    }
    @DisplayName("User는 로그인을 할 수 있고, 로그인시 마지막 로그인 시간이 변경된다.")
    @Test
    void login(){
        // given
        User user = User.builder()
                .id(1L)
                .email(new Email("email@gmail.com"))
                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
                .userStatus(UserStatus.ACTIVE)
                .password(new Password("password"))
                .certificationCode(new TestUuidHolder("1234").random())
                .lastLoginAt(new TestClockHolder(1000L).millis())
                .build();
        ClockHolder clockHolder = new TestClockHolder(2000L);
        // when
        user  = user.login(clockHolder);
        // then
        Assertions.assertThat(user.getEmail().getEmail()).isEqualTo("email@gmail.com");
        Assertions.assertThat(user.getLastLoginAt()).isEqualTo(2000L);
        Assertions.assertThat(user.getPassword().getValue()).isEqualTo("password");
        Assertions.assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        Assertions.assertThat(user.getCertificationCode()).isEqualTo("1234");
    }
    @DisplayName("유효한 인증 코드로 계정을 활성화 할 수 있다.")
    @Test
    void certificate(){
        // given
        User user = User.builder()
                .id(1L)
                .email(new Email("email@gmail.com"))
                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
                .userStatus(UserStatus.PENDING)
                .password(new Password("password"))
                .certificationCode(new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa").random())
                .lastLoginAt(new TestClockHolder(1000L).millis())
                .build();

        // when
        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa");

        // then
        Assertions.assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @DisplayName("User는 잘못된 인증 코드로 계정을 활성화 시도하면 에러를 던진다.")
    @Test
    void certificate_error(){
        // given
        User user = User.builder()
                .id(1L)
                .email(new Email("email@gmail.com"))
                .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
                .userStatus(UserStatus.PENDING)
                .password(new Password("password"))
                .certificationCode(new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa").random())
                .lastLoginAt(new TestClockHolder(1000L).millis())
                .build();

        // when

        // then
        Assertions.assertThatThrownBy(()->{
            user.certificate("boo");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);


    }


}