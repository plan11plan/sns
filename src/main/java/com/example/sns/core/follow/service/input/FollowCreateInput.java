package com.example.sns.core.follow.service.input;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreateInput {
    private final Follower fromUser;
    private final Following toFollowing;
    private final FollowStatus status;

    @Builder
    public FollowCreateInput(Follower fromUser, Following toFollowing) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = FollowStatus.PENDING;

    }
    public static FollowCreateInput initiateFollow(Follower fromUser, Following toFollowing){
        return  FollowCreateInput.builder()
                .fromUser(fromUser)
                .toFollowing(toFollowing)
                .build();
    }

    public Long fromUserId(){
        return fromUser.getId();
    }
    public Long getToFollowingId(){
       return toFollowing.getId();
    }
}
