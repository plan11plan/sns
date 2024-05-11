package com.example.sns.core.user.service.dto;

import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private final Long id;

    private final Email email;
    private final Password password;
    private final Nickname nickname;
    private final Sex sex;
    private final Birthday birthday;
    private final UserStatus status;

    private final LocalDateTime lastLoginAt;
    private final String certificationCode;
    private final LocalDateTime createdAt;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .sex(user.getSex())
                .birthday(user.getBirthday())
                .status(user.getStatus())
                .lastLoginAt(user.getLastLoginAt())
                .certificationCode(user.getCertificationCode())
                .createdAt(user.getCreatedAt())
                .build();
    }
    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
