package com.example.sns.core.user.controller.request;

import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.request.UserCreate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final Email email;
    private final Password password;

    private final Nickname nickname;
    private final UserStatus status;
    private final Sex sex;
    private final Birthday birthday;


    @Builder
    public UserCreateRequest(
            @JsonProperty("email") Email email,
            @JsonProperty("password") Password password,
            @JsonProperty("nickname") Nickname nickname,
            @JsonProperty("status") UserStatus status,
            @JsonProperty("sex") Sex sex,
            @JsonProperty("birthday")
            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.sex = sex;
        this.birthday = new Birthday(birthday);
    }

    public UserCreate toDomainRequest() {
        return UserCreate.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .birthday(birthday)
                .status(UserStatus.PENDING)
                .sex(sex)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
