package com.example.sns.unit.follow.domain.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.common.infrstructure.SystemTimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.request.FollowCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowCreateTest {

    @DisplayName("[생성] 팔로우의 생성 요청 객체를 만든다.")
    @Test
    void FollowCreate(){
        SystemTimeHolder systemTimeHolder = new SystemTimeHolder();
        LocalDateTime createAt = systemTimeHolder.nowDateTime();
        // given
        FollowCreate followCreate = FollowCreate.builder()
                .follower(Follower.fromId(1L))
                .following(Following.fromId(2L))
                .status(FollowStatus.PENDING)
                .createdAt(createAt)
                .build();
        // when
        Follow follow = Follow.from(followCreate);

        // then
        assertAll(
                () -> assertThat(follow.getFollower().getId()).isEqualTo(1L),
                () -> assertThat(follow.getFollowing().getId()).isEqualTo(2L),
                () -> assertThat(follow.getStatus()).isEqualTo(FollowStatus.PENDING),
                () -> assertThat(follow.getCreatedAt()).isEqualTo(createAt),
                () -> assertThat(follow.getModifiedAt()).isNull()
        );

    }

}