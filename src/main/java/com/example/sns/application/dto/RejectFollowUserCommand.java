package com.example.sns.application.dto;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.Follower;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RejectFollowUserCommand {
    private final Follower follower;
    private final Following following;
    private final FollowStatus status;

    @Builder
    public RejectFollowUserCommand(Follower follower, Following following, FollowStatus status) {
        this.follower = follower;
        this.following = following;
        this.status = status;
    }
}
