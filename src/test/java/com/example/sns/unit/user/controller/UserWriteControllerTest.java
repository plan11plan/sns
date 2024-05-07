package com.example.sns.unit.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.sns.core.user.controller.response.UserResponse;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.mock.TestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class UserWriteControllerTest {

    @Test
    void 사용자는_회원_가입을_할_수있고_회원가입된_사용자는_PENDING_상태이다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .uuidHolder(() -> "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
            .build();
        UserCreate userCreate = UserCreate.builder()
            .email("kok202@kakao.com")
            .nickname("kok202")
            .address("Pangyo")
            .build();

        // when
        ResponseEntity<UserResponse> result = testContainer.userWriteController.create(userCreate);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getEmail()).isEqualTo("kok202@kakao.com");
        assertThat(result.getBody().getNickname()).isEqualTo("kok202");
        assertThat(result.getBody().getLastLoginAt()).isNull();
        assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(testContainer.userRepository.getById(1).getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
    }

}
