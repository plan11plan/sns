package com.example.sns.application.dto.follow;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteFollowingUserCommand {
    private final Long fromUserId;
    private final Following toFollowing;
    private final FollowStatus status;


    @Builder
    public DeleteFollowingUserCommand(Long fromUserId, Following toFollowing) {
        this.fromUserId = fromUserId;
        this.toFollowing = toFollowing;
        this.status = FollowStatus.ACCEPTED;
    }


    public Long getFromUserId(){
        return fromUserId;
    }
    public Long getFollowingId(){
        return toFollowing.getId();
    }
}
