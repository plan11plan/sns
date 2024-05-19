package com.example.sns.presentation.follow.controller.request;


import com.example.sns.application.dto.follow.RejectFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowRejectRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowRejectRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }

    public RejectFollowCommand toCommand(){
        return RejectFollowCommand.builder()
                .toFollower(follower)
                .fromUser(following)
                .build();
    }
}
