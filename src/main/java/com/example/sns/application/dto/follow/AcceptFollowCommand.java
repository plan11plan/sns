package com.example.sns.application.dto.follow;

import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AcceptFollowCommand {
    private final Following fromUser;

    private final Follower toFollower;


    @Builder
    public AcceptFollowCommand(Following fromUser, Follower toFollower) {
        this.fromUser = fromUser;
        this.toFollower = toFollower;
    }


    public Long getFromUserId() {
        return  fromUser.getId();
    }

    public Long getToFollowerId() {
        return toFollower.getId();
    }
}

