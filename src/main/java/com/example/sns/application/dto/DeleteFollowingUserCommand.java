package com.example.sns.application.dto;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteFollowingUserCommand {
    private final Long userId;
    private final Following following;
    private final FollowStatus status;


    @Builder
    public DeleteFollowingUserCommand(Long userId, Following following) {
        this.userId = userId;
        this.following = following;
        this.status = FollowStatus.ACCEPTED;
    }


    public Long getUserId(){
        return userId;
    }
    public Long getFollowingId(){
        return following.getId();
    }
}
