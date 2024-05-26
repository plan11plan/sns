package com.example.sns.unit.user.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.sns.common.exception.ResourceNotFoundException;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.mock.user.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserReadServiceTest {

    private UserReadService userReadService;
    private FakeUserRepository fakeUserRepository;

    @BeforeEach
    void setUp() {
        fakeUserRepository = new FakeUserRepository();
        userReadService = UserReadService.builder()
                .userRepository(fakeUserRepository)
                .build();

        fakeUserRepository.save(User.builder()
                .id(UserId.of(1L))
                .email(Email.of("email@gmail.com"))
                .nickname(Nickname.of("nickname"))
                .sex(Sex.M)
                .status(UserStatus.ACTIVE)
                .build());

        fakeUserRepository.save(User.builder()
                .id(UserId.of(2L))
                .email(Email.of("email2@gmail.com"))
                .nickname(Nickname.of("nickname2"))
                .sex(Sex.W)
                .status(UserStatus.PENDING)
                .build());
    }

    @DisplayName("PENDING 상태의 User를 이메일로 조회할 수 없다")
    @Test
    void getUserByEmailWhenPending() {
        // when & then
        assertThatThrownBy(() -> userReadService.getByEmail("email2@gmail.com"))
                .isInstanceOf(ResourceNotFoundException.class);
    }


    @DisplayName("PENDING 상태의 User를 ID로 조회할 수 없다")
    @Test
    void getUserByIdWhenPending() {
        // when & then
        assertThatThrownBy(() -> userReadService.getById(2L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
