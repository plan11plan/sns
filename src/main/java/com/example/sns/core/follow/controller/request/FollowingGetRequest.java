package com.example.sns.core.follow.controller.request;


import com.example.sns.application.dto.GetFollowingUserCommand;
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
