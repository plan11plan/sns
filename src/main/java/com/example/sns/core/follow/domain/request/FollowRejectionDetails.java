package com.example.sns.core.follow.domain.request;

import com.example.sns.core.follow.domain.FollowStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowRejectionDetails {

    FollowStatus status;
    LocalDateTime modifiedAt;


    @Builder
    public FollowRejectionDetails(FollowStatus status, LocalDateTime modifiedAt) {

        this.modifiedAt = modifiedAt;
        this.status =status;
    }


}
