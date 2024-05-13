package com.example.sns.core.follow.controller.request;

import com.example.sns.application.dto.DeleteFollowingUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingDeleteRequest {
    private final Follower fromUser;
    private final Following toFollowing;

    @Builder
    public FollowingDeleteRequest(Follower fromUser, Following toFollowing) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
    }

    public DeleteFollowingUserCommand toCommand() {
        return DeleteFollowingUserCommand
                .builder()
                .fromUserId(fromUser.getId())
                .toFollowing(toFollowing)
                .build();
    }


}
