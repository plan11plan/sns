package com.example.sns.core.user.domain.request;

import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserUpdate {

    private final Email email;
    private final Password password;
    private final Nickname nickname;


    @Builder
    public UserUpdate(
           @JsonProperty("email") Email email,
            @JsonProperty("password") Password password,
           @JsonProperty("nickname") Nickname nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}


