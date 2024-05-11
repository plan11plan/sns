package com.example.sns.core.follow.controller.request;

import com.example.sns.application.dto.DeleteFollowerUserCommand;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.Follower;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowerDeleteRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowerDeleteRequest(Follower follower, Following following, FollowStatus status) {
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
