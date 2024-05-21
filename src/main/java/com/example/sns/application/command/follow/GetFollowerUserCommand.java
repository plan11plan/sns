package com.example.sns.application.command.follow;

import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFollowerUserCommand {
    private final Following following;

    @Builder
    public GetFollowerUserCommand(Following following) {
        this.following = following;
    }

    public Long getFollowingId() {
        return following.getId();
    }
}
