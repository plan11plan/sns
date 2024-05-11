package com.example.sns.core.follow.controller.request;


import com.example.sns.application.dto.RejectFollowUserCommand;
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

    public RejectFollowUserCommand toCommand(){
        return RejectFollowUserCommand.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
