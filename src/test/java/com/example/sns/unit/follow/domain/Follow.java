package com.example.sns.unit.follow.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.common.infrstructure.SystemTimeHolder;
import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.request.FollowCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowTest {

    @DisplayName("Follow 객체를 생성할 수 있다.")
    @Test
    void createFollow() {
        // given
        LocalDateTime now = LocalDateTime.now();
        FollowCreate followCreate = FollowCreate.builder()
                .follower(Follower.fromId(1L))
                .following(Following.fromId(2L))
                .status(FollowStatus.PENDING)
                .createdAt(now)
                .modifiedAt(null)
                .build();

        // when
        Follow follow = Follow.from(followCreate);

        // then
        assertAll(
                () -> assertThat(follow.getFollower().getId()).isEqualTo(1L),
                () -> assertThat(follow.getFollowing().getId()).isEqualTo(2L),
                () -> assertThat(follow.getStatus()).isEqualTo(FollowStatus.PENDING),
                () -> assertThat(follow.getCreatedAt()).isEqualTo(now),
                () -> assertThat(follow.getModifiedAt()).isNull()
        );
    }

    @DisplayName("Follow 상태를 ACCEPTED로 변경할 수 있다.")
    @Test
    void acceptFollow() {
        // given
        Follow follow = createPendingFollow();
        TimeHolder timeHolder = new SystemTimeHolder();

        // when
        Follow acceptedFollow = follow.accept(timeHolder);

        // then
        assertAll(
                () -> assertThat(acceptedFollow.getStatus()).isEqualTo(FollowStatus.ACCEPTED),
                () -> assertThat(acceptedFollow.getModifiedAt()).isNotNull()
        );
    }

    @DisplayName("Pending 상태가 아닌 Follow를 accept할 경우 예외를 던진다.")
    @Test
    void acceptNonPendingFollowShouldThrow() {
        // given
        Follow follow = createAcceptedFollow();
        TimeHolder timeHolder = new SystemTimeHolder();

        // expected
        assertThatThrownBy(() -> follow.accept(timeHolder))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("Follow 상태를 REJECTED로 변경할 수 있다.")
    @Test
    void rejectFollow() {
        // given
        Follow follow = createPendingFollow();
        TimeHolder timeHolder = new SystemTimeHolder();

        // when
        Follow rejectedFollow = follow.reject(timeHolder);

        // then
        assertAll(
                () -> assertThat(rejectedFollow.getStatus()).isEqualTo(FollowStatus.REJECTED),
                () -> assertThat(rejectedFollow.getModifiedAt()).isNotNull()
        );
    }

    @DisplayName("Follow 상태를 CANCELLED로 변경할 수 있다.")
    @Test
    void cancelFollow() {
        // given
        Follow follow = createAcceptedFollow();
        TimeHolder timeHolder = new SystemTimeHolder();

        // when
        Follow cancelledFollow = follow.cancel(timeHolder);

        // then
        assertAll(
                () -> assertThat(cancelledFollow.getStatus()).isEqualTo(FollowStatus.CANCELLED),
                () -> assertThat(cancelledFollow.getModifiedAt()).isNotNull()
        );
    }

    private Follow createPendingFollow() {
        return Follow.builder()
                .id(1L)
                .follower(Follower.fromId(1L))
                .following(Following.fromId(2L))
                .status(FollowStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Follow createAcceptedFollow() {
        return Follow.builder()
                .id(1L)
                .follower(Follower.fromId(1L))
                .following(Following.fromId(2L))
                .status(FollowStatus.ACCEPTED)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
