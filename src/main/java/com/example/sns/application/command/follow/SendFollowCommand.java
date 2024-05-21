package com.example.sns.application.command.follow;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SendFollowCommand {
    private final Follower fromUser;
    private final Following toFollowing;
    private final FollowStatus status;

    @Builder
    public SendFollowCommand(Follower fromUser, Following toFollowing, FollowStatus status) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = status;
    }
    public Long getUserId(){
        return fromUser.getId();
    }
    public Long getFollowingId(){
        return toFollowing.getId();
    }
}
