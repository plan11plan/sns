package com.example.sns.application.dto;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteFollowerUserCommand {
    private final Long userId;
    private final Follower follower;
    private final FollowStatus status;

    @Builder
    public DeleteFollowerUserCommand(Long userId,Follower follower) {
        this.userId =userId;
        this.follower = follower;
        this.status = FollowStatus.ACCEPTED;
    }
    public Long getUserId(){
        return userId;
    }

    public Long getFollowerId(){
        return follower.getId();
    }
}
