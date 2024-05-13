package com.example.sns.core.follow.controller.request;


import com.example.sns.application.dto.AcceptFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowAcceptRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowAcceptRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }

    public AcceptFollowCommand toCommand(){
        return AcceptFollowCommand.builder()
                .toFollower(follower)
                .fromUser(following)
                .build();
    }
}
