package com.example.sns.core.follow.service.dto.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowerDelete {
    private final Following userId;
    private final Follower follower;
    private final FollowStatus status;

    // 생성자를 private으로 만들어 객체 생성을 제한합니다.
    @Builder
    public FollowerDelete(Following userId, Follower follower) {
        this.userId =userId;
        this.follower = follower;
        this.status = FollowStatus.ACCEPTED;
    }

    // 팔로워와 팔로잉의 ID를 사용하여 FollowerDelete 객체를 생성하는 팩토리 메소드
    public static FollowerDelete fromIds(Long userId, Long followerId) {
        return FollowerDelete.builder()
                .userId(Following.fromId(userId))
                .follower(Follower.fromId(followerId))
                .build();

    }
    public Long getFollowerId(){
        return follower.getId();
    }
    public Long getUserId(){
        return userId.getId();
    }
}