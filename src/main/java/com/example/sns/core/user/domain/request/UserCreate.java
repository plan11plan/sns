package com.example.sns.core.user.domain.request;

import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UserCreate(
        String name,
        int age,
        Sex sex,
        String nickname,
        String email,
        String password,
        UserStatus userStatus,
        LocalDate birthday,
        LocalDateTime createdAt
) {}