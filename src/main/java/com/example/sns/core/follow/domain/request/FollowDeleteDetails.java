package com.example.sns.core.follow.domain.request;

import com.example.sns.core.follow.domain.FollowStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowDeleteDetails {
    FollowStatus status;
    LocalDateTime modifiedAt;


    @Builder
    public FollowDeleteDetails(FollowStatus status, LocalDateTime modifiedAt) {
        this.status = status;
        this.modifiedAt = modifiedAt;
    }
}
