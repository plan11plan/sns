package com.example.sns.unit.user.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.core.user.domain.service.UserUpdateService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.mock.TestTimeHolder;
import com.example.sns.mock.user.FakeNicknameHistoryRepository;
import com.example.sns.mock.user.FakeUserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserUpdateServiceTest {

    private UserUpdateService userUpdateService;
    private FakeUserRepository fakeUserRepository;
    private FakeNicknameHistoryRepository fakeNicknameHistoryRepository;
    private TimeHolder timeHolder;

    @BeforeEach
    void setUp() {
        fakeUserRepository = new FakeUserRepository();
        fakeNicknameHistoryRepository = new FakeNicknameHistoryRepository();
        timeHolder = new TestTimeHolder(LocalDateTime.of(2023, 1, 1, 0, 0));

        userUpdateService = UserUpdateService.builder()
                .userRepository(fakeUserRepository)
                .nicknameHistoryRepository(fakeNicknameHistoryRepository)
                .timeHolder(timeHolder)
                .build();

        fakeUserRepository.save(User.builder()
                .id(UserId.of(1L))
                .email(Email.of("email@example.com"))
                .nickname(Nickname.of("nickname"))
                .sex(Sex.M)
                .birthday(Birthday.of(LocalDate.of(1990, 1, 1)))
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build());
    }

    @DisplayName("[수정] 유저 정보를 수정할 수 있다.")
    @Test
    void updateUser() {
        // given
        Long userId = 1L;
        UserUpdate userUpdate = UserUpdate.builder()
                .email(Email.of("updated@example.com"))
                .password(Password.of("updated-password"))
                .nickname(Nickname.of("updated-nickname"))
                .build();
        System.out.println("1");

        // when
        UserOutput userOutput = userUpdateService.update(userId, userUpdate);
        System.out.println(userOutput.getRole());


    }
}
