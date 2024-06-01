package com.example.sns.core.follow.domain.service.input;


import com.example.sns.core.follow.domain.FollowStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowGetInput {
    private final Long userId;
    private final FollowStatus status;

    @Builder
    private FollowGetInput(Long userId, FollowStatus status) {
        this.userId = userId;
        this.status = status;
    }
    public static FollowGetInput fromId(Long userid){
        return FollowGetInput.builder()
                .userId(userid)
                .status(FollowStatus.ACCEPTED)
                .build();
    }
}
