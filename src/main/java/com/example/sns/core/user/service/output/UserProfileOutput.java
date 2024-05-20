package com.example.sns.core.user.service.output;

import com.example.sns.core.user.domain.entity.vo.UserProfile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileOutput {
    private Long userId;
    private String nickname;
    private String sex;

    public static UserProfileOutput from(UserProfile userProfile) {
        return UserProfileOutput.builder()
                .userId(userProfile.getUserId())
                .nickname(userProfile.getNickname())
                .sex(userProfile.getSex().name())
                .build();
    }
}