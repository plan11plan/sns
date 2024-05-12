package com.example.sns.application.dto;

import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AcceptFollowUserCommand {

    private final Follower follower;
    private final Following following;

    @Builder
    public AcceptFollowUserCommand(Follower follower, Following following) {
        this.follower = follower;
        this.following = following;
    }
    public Long getFollowerId(){
        return follower.getId();
    }
    public Long getFollowingId(){
        return following.getId();
    }
}

