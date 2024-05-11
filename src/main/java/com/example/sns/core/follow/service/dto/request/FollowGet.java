package com.example.sns.core.follow.service.dto.request;


import com.example.sns.core.follow.domain.FollowStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowGet {
    private final Long userId;
    private final FollowStatus status;

    @Builder
    private FollowGet(Long userId, FollowStatus status) {
        this.userId = userId;
        this.status = status;
    }
    public static FollowGet fromId(Long userid){
        return FollowGet.builder()
                .userId(userid)
                .status(FollowStatus.ACCEPTED)
                .build();
    }
}
