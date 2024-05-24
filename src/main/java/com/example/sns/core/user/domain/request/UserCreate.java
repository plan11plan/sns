package com.example.sns.core.user.domain.request;

import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreate {
    private Email email;
    private Password password;
    private Nickname nickname;
    private UserStatus status;
    private Sex sex;
    private Birthday birthday;
    private LocalDateTime createdAt;

    @Builder
    public UserCreate(Email email, Password password, Nickname nickname, Sex sex, Birthday birthday,
                      LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = UserStatus.PENDING;
        this.sex = sex;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }
}