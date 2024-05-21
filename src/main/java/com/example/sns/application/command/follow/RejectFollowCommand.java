package com.example.sns.application.command.follow;

import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RejectFollowCommand {

    private final Following fromUser;
    private final Follower ToFollower;

    @Builder
    public RejectFollowCommand(Following fromUser, Follower toFollower) {
        this.fromUser = fromUser;
        this.ToFollower = toFollower;
    }
    public Long getToFollowerId(){
        return ToFollower.getId();
    }
    public Long getFromUserId(){
        return fromUser.getId();
    }
}
