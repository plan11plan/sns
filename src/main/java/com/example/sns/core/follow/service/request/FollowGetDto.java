package com.example.sns.core.follow.service.request;


import com.example.sns.core.follow.domain.FollowStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowGetDto {
    private final Long userId;
    private final FollowStatus status;

    @Builder
    private FollowGetDto(Long userId, FollowStatus status) {
        this.userId = userId;
        this.status = status;
    }
    public static FollowGetDto fromId(Long userid){
        return FollowGetDto.builder()
                .userId(userid)
                .status(FollowStatus.ACCEPTED)
                .build();
    }
}
