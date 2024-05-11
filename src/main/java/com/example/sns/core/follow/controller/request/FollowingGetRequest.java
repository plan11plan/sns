package com.example.sns.core.follow.controller.request;


import com.example.sns.application.dto.GetFollowingUserCommand;
import com.example.sns.core.follow.domain.Follower;
import lombok.Getter;

@Getter
public class FollowingGetRequest {

    Follower aFollower;

    public FollowingGetRequest(Follower aFollower) {
        this.aFollower = aFollower;
    }

    public GetFollowingUserCommand toCommand(){
        return GetFollowingUserCommand.builder()
                .follower(aFollower)
                .build();

    }
}
