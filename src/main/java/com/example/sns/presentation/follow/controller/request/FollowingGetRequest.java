package com.example.sns.presentation.follow.controller.request;


import com.example.sns.application.command.follow.GetFollowingUserCommand;
import com.example.sns.core.follow.domain.Follower;
import lombok.Getter;

@Getter
public class FollowingGetRequest {

    Follower follower;

    public FollowingGetRequest(Follower follower) {
        this.follower = follower;
    }

    public GetFollowingUserCommand toCommand(){
        return GetFollowingUserCommand.builder()
                .follower(follower)
                .build();

    }
}
