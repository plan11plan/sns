package com.example.sns.core.follow.controller.request;

import com.example.sns.application.dto.CreateFollowUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreateRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowCreateRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }

    public CreateFollowUserCommand toCommand(){
        return CreateFollowUserCommand.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
