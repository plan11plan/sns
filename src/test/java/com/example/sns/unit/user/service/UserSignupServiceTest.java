package com.example.sns.unit.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.service.CertificationService;
import com.example.sns.core.user.service.UserSignupService;
import com.example.sns.core.user.service.input.UserCreateInput;
import com.example.sns.core.user.service.output.UserOutput;
import com.example.sns.mock.TestTimeHolder;
import com.example.sns.mock.TestUuidHolder;
import com.example.sns.mock.user.FakeMailSender;
import com.example.sns.mock.user.FakeUserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSignupServiceTest {

    private UserSignupService userSignupService;
    private FakeUserRepository fakeUserRepository;
    private FakeMailSender fakeMailSender;

    @BeforeEach
    void setUp() {
        fakeMailSender = new FakeMailSender();
        fakeUserRepository = new FakeUserRepository();
        userSignupService = UserSignupService.builder()
                .userRepository(fakeUserRepository)
                .certificationService(new CertificationService(fakeMailSender))
                .timeHolder(   new TestTimeHolder(LocalDateTime.of(2023, 1, 1, 0, 0))
)
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
        UserOutput result = userSignupService.signup(userCreateInput);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getNickname()).isEqualTo("testNickname");
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING.toString());
    }
}
