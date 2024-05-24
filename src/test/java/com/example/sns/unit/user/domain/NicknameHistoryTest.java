package com.example.sns.unit.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.NicknameHistoryId;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.request.NicknameHistoryCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameHistoryTest {

    @DisplayName("[생성] NicknameHistory는 NicknameHistoryCreate 객체로 생성할 수 있다.")
    @Test
    void byNicknameHistoryCreate() {
        // given
        LocalDateTime createTime = LocalDateTime.now();
        UserId userId = UserId.of(1L);
        Nickname nickname = Nickname.of("nickname");
        NicknameHistoryCreate nicknameHistoryCreate = NicknameHistoryCreate.builder()
                .userId(userId)
                .nickname(nickname)
                .build();

        // when
        NicknameHistory nicknameHistory = NicknameHistory.from(nicknameHistoryCreate, createTime);

        // then
        assertAll(
                () -> assertThat(nicknameHistory.getUserId().getValue()).isEqualTo(1L),
                () -> assertThat(nicknameHistory.getNickname().getValue()).isEqualTo("nickname"),
                () -> assertThat(nicknameHistory.getCreatedAt()).isEqualTo(createTime)
        );
    }

    @DisplayName("[ID 조회] NicknameHistory의 ID 값을 올바르게 조회할 수 있다.")
    @Test
    void getNicknameHistoryIdValue() {
        // given
        NicknameHistoryId id = NicknameHistoryId.of(1L);
        UserId userId = UserId.of(1L);
        Nickname nickname = Nickname.of("nickname");
        LocalDateTime createTime = LocalDateTime.now();

        NicknameHistory nicknameHistory = NicknameHistory.builder()
                .id(id)
                .userId(userId)
                .nickname(nickname)
                .createdAt(createTime)
                .build();

        // when
        Long idValue = nicknameHistory.getNicknameHistoryIdValue();

        // then
        assertThat(idValue).isEqualTo(1L);
    }

    @DisplayName("[User ID 조회] NicknameHistory의 User ID 값을 올바르게 조회할 수 있다.")
    @Test
    void getUserIdValue() {
        // given
        UserId userId = UserId.of(1L);
        Nickname nickname = Nickname.of("nickname");
        LocalDateTime createTime = LocalDateTime.now();

        NicknameHistory nicknameHistory = NicknameHistory.builder()
                .userId(userId)
                .nickname(nickname)
                .createdAt(createTime)
                .build();

        // when
        Long userIdValue = nicknameHistory.getUserIdValue();

        // then
        assertThat(userIdValue).isEqualTo(1L);
    }

    @DisplayName("[닉네임 조회] NicknameHistory의 Nickname 값을 올바르게 조회할 수 있다.")
    @Test
    void getNicknameValue() {
        // given
        UserId userId = UserId.of(1L);
        Nickname nickname = Nickname.of("nickname");
        LocalDateTime createTime = LocalDateTime.now();

        NicknameHistory nicknameHistory = NicknameHistory.builder()
                .userId(userId)
                .nickname(nickname)
                .createdAt(createTime)
                .build();

        // when
        String nicknameValue = nicknameHistory.getNicknameValue();

        // then
        assertThat(nicknameValue).isEqualTo("nickname");
    }
}
