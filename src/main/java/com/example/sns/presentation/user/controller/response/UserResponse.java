package com.example.sns.presentation.user.controller.response;

import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.service.dto.UserDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String email;
    private String nickname;
    private UserStatus status;
    private LocalDateTime lastLoginAt;

    @Builder
    private UserResponse(Long id, String email, String nickname, UserStatus status, LocalDateTime lastLoginAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
    }

    public static UserResponse from(UserDto user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail().getValue())
                .nickname(user.getNickname().getValue())
                .status(user.getStatus())
                .lastLoginAt(user.getLastLoginAt())
                .build();

    }
}
