package com.example.sns.core.follow.service.dto.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowAccept {
    UserDto follower;
    UserDto following;

    FollowStatus followStatus;

    @Builder
    public FollowAccept(UserDto follower, UserDto following) {
        this.follower = follower;
        this.following = following;
        this.followStatus = FollowStatus.PENDING;
    }
    public Long getFollowerId(){
        return follower.getId();
    }
    public Long getFollowingId(){
        return following.getId();
    }
}
