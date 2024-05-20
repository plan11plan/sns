package com.example.sns.core.follow.service.input;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowAcceptInput {
    private final Following fromUser;
    private final Follower toFollower;
    private final FollowStatus status;

    @Builder
    public FollowAcceptInput(Following fromUser, Follower toFollower) {
        this.fromUser = fromUser;
        this.toFollower = toFollower;
        this.status = FollowStatus.PENDING;
    }
    public Long getFromUserId(){
        return fromUser.getId();
    }
    public Long getToFollowerId(){
        return toFollower.getId();
    }
}
