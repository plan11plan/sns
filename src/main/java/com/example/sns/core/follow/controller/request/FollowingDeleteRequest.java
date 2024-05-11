package com.example.sns.core.follow.controller.request;

import com.example.sns.application.dto.DeleteFollowingUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingDeleteRequest {
    private final Follower follower;
    private final Following following;

    @Builder
    public FollowingDeleteRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }

    public DeleteFollowingUserCommand toCommand() {
        return DeleteFollowingUserCommand
                .builder()
                .userId(follower.getId())
                .following(following)
                .build();
    }


}
