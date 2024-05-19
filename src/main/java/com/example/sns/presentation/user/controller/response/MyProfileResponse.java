package com.example.sns.presentation.user.controller.response;

import com.example.sns.core.user.service.dto.UserDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyProfileResponse {

    private Long id;
    private String email;
    private String nickname;
    private String status;
    private LocalDateTime lastLoginAt;

    public static MyProfileResponse from(UserDto user){
        return MyProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .status(user.getStatus())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }

}
