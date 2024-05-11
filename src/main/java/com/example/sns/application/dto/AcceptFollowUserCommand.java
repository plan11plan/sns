package com.example.sns.application.dto;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AcceptFollowUserCommand {

    private final Follower follower;
    private final Following following;
    private final FollowStatus status;


    @Builder
    public AcceptFollowUserCommand(Follower follower, Following following, FollowStatus status) {
        this.follower = follower;
        this.following = following;
        this.status = status;
    }
    public java.lang.Long getFollowerId(){
        return follower.getId();
    }
    public java.lang.Long getFollowingId(){
        return following.getId();
    }
}

