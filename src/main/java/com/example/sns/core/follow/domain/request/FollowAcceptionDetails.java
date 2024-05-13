package com.example.sns.core.follow.domain.request;

import com.example.sns.core.follow.domain.FollowStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowAcceptionDetails {
    LocalDateTime modifiedAt;
    FollowStatus status;

    @Builder
    public FollowAcceptionDetails(FollowStatus status, LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        this.status = status;
    }


}
