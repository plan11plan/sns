package com.example.sns.core.follow.service.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowRejectDto {
    Following fromUser;

    Follower toFollower;
    FollowStatus status;


    @Builder
    public FollowRejectDto(Following fromUser, Follower toFollower) {
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
