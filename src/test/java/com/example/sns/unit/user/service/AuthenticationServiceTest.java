package com.example.sns.unit.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.sns.core.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.AuthenticationService;
import com.example.sns.mock.TestTimeHolder;
import com.example.sns.mock.user.FakeNicknameHistoryRepository;
import com.example.sns.mock.user.FakeUserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private FakeUserRepository fakeUserRepository;
    private FakeNicknameHistoryRepository fakeNicknameHistoryRepository;
    private TimeHolder timeHolder;

    @BeforeEach
    void setUp() {
        fakeUserRepository = new FakeUserRepository();
        fakeNicknameHistoryRepository = new FakeNicknameHistoryRepository();
        timeHolder = new TestTimeHolder(LocalDateTime.now());
        authenticationService = AuthenticationService.builder()
                .userRepository(fakeUserRepository)
                .nicknameHistoryRepository(fakeNicknameHistoryRepository)
                .timeHolder(timeHolder)
                .build();
    }

    @DisplayName("User는 로그인할 수 있다")
    @Test
    void loginUser() {
        // given
        User user = User.builder()
                .nickname(Nickname.of("testNickname"))
                .email(Email.of("test@gmail.com"))
                .password(Password.of("password"))
                .sex(Sex.M)
                .status(UserStatus.ACTIVE)
                .build();
        User savedUser = fakeUserRepository.save(user);

        // 로그를 추가하여 저장된 ID를 확인
        System.out.println("Saved User ID: " + savedUser.getUserIdValue());

        // when
        authenticationService.login(savedUser.getUserIdValue());

        // then
        User updatedUser = fakeUserRepository.getById(savedUser.getUserIdValue());
    }

    @DisplayName("User는 인증할 수 있다")
    @Test
    void verifyEmail() {
        // given
        User user = User.builder()
                .nickname(Nickname.of("testNickname"))
                .email(Email.of("test@gmail.com"))
                .password(Password.of("password"))
                .sex(Sex.M)
                .status(UserStatus.PENDING)
                .certificationCode("1234")
                .build();
        User savedUser = fakeUserRepository.save(user);

        // 로그를 추가하여 저장된 ID를 확인
        System.out.println("Saved User ID: " + savedUser.getUserIdValue());

        // when
        authenticationService.verifyEmail(savedUser.getUserIdValue(), "1234");

        // then
        User updatedUser = fakeUserRepository.getById(savedUser.getUserIdValue());
        assertThat(updatedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(fakeNicknameHistoryRepository.findAllByUserId(savedUser.getUserIdValue()).isPresent()).isTrue();
    }

    @DisplayName("User는 잘못된 인증 코드로 인증할 수 없다")
    @Test
    void verifyEmailWithWrongCode() {
        // given
        User user = User.builder()
                .nickname(Nickname.of("testNickname"))
                .email(Email.of("test@gmail.com"))
                .password(Password.of("password"))
                .sex(Sex.M)
                .status(UserStatus.PENDING)
                .certificationCode("1234")
                .build();
        User savedUser = fakeUserRepository.save(user);

        // 로그를 추가하여 저장된 ID를 확인
        System.out.println("Saved User ID: " + savedUser.getUserIdValue());

        // when & then
        assertThatThrownBy(() -> authenticationService.verifyEmail(savedUser.getUserIdValue(), "wrongCode"))
                .isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}
