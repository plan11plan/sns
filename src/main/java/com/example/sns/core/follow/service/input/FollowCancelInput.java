package com.example.sns.core.follow.service.input;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCancelInput {
    Follower fromUser;
    Following toFollowing;

    FollowStatus status;

    @Builder
    public FollowCancelInput(Follower fromUser, Following toFollowing) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = FollowStatus.PENDING;
    }

    public Long getFromUserId(){
        return fromUser.getId();
    }
    public Long getToFollowingId(){
        return toFollowing.getId();
    }
}
