package com.example.sns.application.dto.follow;


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
