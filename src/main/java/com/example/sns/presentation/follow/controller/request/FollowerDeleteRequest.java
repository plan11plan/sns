package com.example.sns.presentation.follow.controller.request;

import com.example.sns.application.command.follow.DeleteFollowerUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowerDeleteRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowerDeleteRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }

    public DeleteFollowerUserCommand toCommand(){
        return DeleteFollowerUserCommand.builder()
                .follower(follower)
                .userId(following.getId())
                .build();
    }


}
