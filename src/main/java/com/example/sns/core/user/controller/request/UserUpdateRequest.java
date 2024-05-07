package com.example.sns.core.user.controller.request;

import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private final Email email;
    private final Password password;
    private final Nickname nickname;

    public UserUpdateRequest(
            @JsonProperty("email") Email email,
            @JsonProperty("password") Password password,
            @JsonProperty("nickname") Nickname nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public UserUpdate toDomainRequest() {
        return UserUpdate.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

    }
}
