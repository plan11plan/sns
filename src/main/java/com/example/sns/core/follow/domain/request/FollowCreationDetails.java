package com.example.sns.core.follow.domain.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreationDetails {
    private final Follower fromUser;
    private final Following toFollowing;
    private final FollowStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public FollowCreationDetails(Follower fromUser, Following toFollowing,FollowStatus status, LocalDateTime createdAt,
                                 LocalDateTime modifiedAt) {
        this.fromUser = fromUser;
        this.toFollowing = toFollowing;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
