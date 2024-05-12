package com.example.sns.core.follow.controller.request;

import com.example.sns.application.dto.CreateFollowUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreateRequest {
    private final Long followerId;
    private final Long followingId;

    @Builder
    public FollowCreateRequest(
            @JsonProperty("followerId") Long followerId,
            @JsonProperty("followingId") Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public CreateFollowUserCommand toCommand() {
        return CreateFollowUserCommand.builder()
                .follower(Follower.fromId(followerId))
                .following(Following.fromId(followingId))
                .build();
    }
}
