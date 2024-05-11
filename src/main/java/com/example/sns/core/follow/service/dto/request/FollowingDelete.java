package com.example.sns.core.follow.service.dto.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingDelete {
    private final Follower userId;
    private final Following following;
    private final FollowStatus status;

    // 생성자를 private으로 만들어 객체 생성을 제한합니다.


    @Builder
    public FollowingDelete(Follower userId, Following following, FollowStatus status) {
        this.userId = userId;
        this.following = following;
        this.status = FollowStatus.ACCEPTED;
    }

    // 팔로워와 팔로잉의 ID를 사용하여 FollowerDelete 객체를 생성하는 팩토리 메소드
    public static FollowingDelete fromIds(Long userId, Long followingId) {
        return FollowingDelete.builder()
                .userId(Follower.fromId(userId))
                .following(Following.fromId(followingId))
                .build();
    }
    public Long getUserId(){
        return userId.getId();
    }
    public Long getFollowingId(){
        return following.getId();
    }
}