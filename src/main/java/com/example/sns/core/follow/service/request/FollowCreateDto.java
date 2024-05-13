package com.example.sns.core.follow.service.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreateDto {
    private final Follower fromUser;
    private final Following toFollowing;
    private final FollowStatus status;

    @Builder
    public FollowCreateDto(Follower fromUser, Following toFollowing) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = FollowStatus.PENDING;

    }
    public static FollowCreateDto initiateFollow(Follower fromUser, Following toFollowing){
        return  FollowCreateDto.builder()
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
