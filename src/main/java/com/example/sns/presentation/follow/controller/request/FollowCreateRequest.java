package com.example.sns.presentation.follow.controller.request;

import com.example.sns.application.command.follow.SendFollowCommand;
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

    public SendFollowCommand toCommand() {
        return SendFollowCommand.builder()
                .fromUser(Follower.fromUserId(followerId))
                .toFollowing(Following.fromUserId(followingId))
                .build();
    }
}
