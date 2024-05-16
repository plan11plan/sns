package com.example.sns.application.dto.follow;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CancelFollowCommand {
    private final Long userId;
    private final Following following;
    private final FollowStatus status;

    @Builder
    public CancelFollowCommand(Long userId, Following following) {
        this.userId = userId;
        this.following = following;
        this.status = FollowStatus.PENDING;
    }
    public Long getUserId(){
        return userId;
    }
    public Long getFollowingId(){
        return following.getId();
    }
}
