package com.example.sns.core.user.service.input;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateInput {
    String email;
    LocalDate birthDay;
    String sex;
    String password;
    String nickname;

    @Builder
    public UserCreateInput(String email, LocalDate birthDay, String sex, String password,
                           String nickname) {
        this.email = email;
        this.birthDay = birthDay;
        this.sex = sex;
        this.password = password;
        this.nickname = nickname;
    }
}
