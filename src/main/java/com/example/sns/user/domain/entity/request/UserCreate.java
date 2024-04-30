package com.example.sns.user.domain.entity.request;

import com.example.sns.user.domain.entity.Sex;
import com.example.sns.user.domain.entity.UserStatus;
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