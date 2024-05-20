package com.example.sns.core.follow.service.input;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowerDeleteInput {
    private final Following fromUser;
    private final Follower toFollower;
    private final FollowStatus status;

    // 생성자를 private으로 만들어 객체 생성을 제한합니다.
    @Builder
    public FollowerDeleteInput(Following fromUser, Follower toFollower) {
        this.fromUser = fromUser;
        this.toFollower = toFollower;
        this.status = FollowStatus.ACCEPTED;
    }


    public Long getToFollowerId(){
        return toFollower.getId();
    }
    public Long getFromUserId(){
        return fromUser.getId();
    }
}