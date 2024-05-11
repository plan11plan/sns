package com.example.sns.core.follow.service.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowingDto {

    Long memberId;

    @Builder
    public FollowingDto(Long memberId) {
        this.memberId = memberId;
    }
}
