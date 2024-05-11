package com.example.sns.core.follow.domain.request;

import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreate {
    private final Follower follower;
    private final Following following;
    private final FollowStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public FollowCreate(Follower follower, Following following, FollowStatus status, LocalDateTime createdAt,
                        LocalDateTime modifiedAt) {
        this.follower = follower;
        this.following = following;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
