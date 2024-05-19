package com.example.sns.presentation.follow.controller.request;

import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingCancelRequest {
    private final Follower follower;
    private final Following following;


    @Builder
    public FollowingCancelRequest(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }
}
