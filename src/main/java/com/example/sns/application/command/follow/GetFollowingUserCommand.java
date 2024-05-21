package com.example.sns.application.command.follow;


import com.example.sns.core.follow.domain.Follower;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFollowingUserCommand {

    private final Follower follower;

    @Builder
    public GetFollowingUserCommand(Follower follower) {
        this.follower = follower;
    }

    public java.lang.Long getFollowerId(){
        return follower.getId();
    }
}
