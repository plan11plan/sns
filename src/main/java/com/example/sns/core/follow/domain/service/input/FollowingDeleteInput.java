package com.example.sns.core.follow.domain.service.input;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingDeleteInput {
    private final Follower fromUser;
    private final Following toFollowing;
    private final FollowStatus status;

    // 생성자를 private으로 만들어 객체 생성을 제한합니다.


    @Builder
    public FollowingDeleteInput(Follower fromUser, Following toFollowing) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = FollowStatus.ACCEPTED;
    }

    // 팔로워와 팔로잉의 ID를 사용하여 FollowerDeleteInput 객체를 생성하는 팩토리 메소드

    public Long getFromUser(){
        return fromUser.getId();
    }
    public Long getFollowingId(){
        return toFollowing.getId();
    }
}