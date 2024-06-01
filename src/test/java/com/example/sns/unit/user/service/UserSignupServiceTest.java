package com.example.sns.unit.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.service.CertificationService;
import com.example.sns.core.user.domain.service.UserSignupService;
import com.example.sns.core.user.domain.service.input.UserCreateInput;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.mock.TestTimeHolder;
import com.example.sns.mock.TestUuidHolder;
import com.example.sns.mock.user.FakeMailSender;
import com.example.sns.mock.user.FakeUserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserSignupServiceTest {

    private UserSignupService userSigninService;
    private FakeUserRepository fakeUserRepository;
    private FakeMailSender fakeMailSender;
    private  PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        fakeMailSender = new FakeMailSender();
        fakeUserRepository = new FakeUserRepository();
        passwordEncoder = new BCryptPasswordEncoder();
        userSigninService = UserSignupService.builder()
                .userRepository(fakeUserRepository)
                .certificationService(new CertificationService(fakeMailSender))
                .timeHolder(   new TestTimeHolder(LocalDateTime.of(2023, 1, 1, 0, 0))
)
                .passwordEncoder(passwordEncoder)
                .uuidHolder(new TestUuidHolder("1234"))
                .build();
    }

    @DisplayName("User를 생성할 수 있다")
    @Test
    void createUser() {
        // given
        UserCreateInput userCreateInput = UserCreateInput.builder()
                .email("test@gmail.com")
                .nickname("testNickname")
                .password("password")
                .birthDay(LocalDate.now())
                .sex("M")
                .build();

        // when
        UserOutput result = userSigninService.signup(userCreateInput);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getNickname()).isEqualTo("testNickname");
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING.toString());
    }
}
