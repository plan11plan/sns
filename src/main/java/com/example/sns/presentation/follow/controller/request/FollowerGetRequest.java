package com.example.sns.presentation.follow.controller.request;

import com.example.sns.application.dto.follow.GetFollowerUserCommand;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowerGetRequest {

    Following following;

    @Builder
    public FollowerGetRequest(Following following) {
        this.following = following;
    }
    public GetFollowerUserCommand toCommand(){
        return GetFollowerUserCommand.builder()
                .following(following)
                .build();

    }
}
