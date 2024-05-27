package com.example.sns.presentation.user.controller.request;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequest {
    private final String email;
    private final String password;

    private final String nickname;
    private final String sex;
    private final LocalDate birthday;


    @Builder
    public SignupRequest(String email, String password, String nickname, String sex, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
    }

}
