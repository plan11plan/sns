package com.example.sns.core.user.service.dto;

import com.example.sns.core.user.domain.entity.root.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private final Long id;

    private final String email;
    private final String password;
    private final String  nickname;
    private final String sex;
    private final String status;
    private final LocalDate birthday;


    private final LocalDateTime lastLoginAt;
    private final String certificationCode;
    private final LocalDateTime createdAt;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmailValue())
                .password(user.getPasswordValue())
                .nickname(user.getNicknameValue())
                .sex(user.getSexValue())
                .birthday(user.getBirthdayValue())
                .status(user.getStatusValue())
                .lastLoginAt(user.getLastLoginAt())
                .certificationCode(user.getCertificationCode())
                .createdAt(user.getCreatedAt())
                .build();
    }
    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
